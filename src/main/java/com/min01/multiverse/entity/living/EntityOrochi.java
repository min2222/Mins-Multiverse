package com.min01.multiverse.entity.living;

import java.util.ArrayList;
import java.util.List;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.misc.KinematicChain;
import com.min01.multiverse.misc.KinematicChain.ChainSegment;
import com.min01.multiverse.misc.SmoothAnimationState;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntityOrochi extends AbstractAnimatableMonster
{
	public static final EntityDataAccessor<Boolean> IS_RIDING = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.BOOLEAN);
	
	public final List<OrochiChain> chains = new ArrayList<>();
	
	public EntityOrochi(EntityType<? extends Monster> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 600.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.35F)
        		.add(Attributes.ATTACK_DAMAGE, 5.0F)
        		.add(Attributes.FOLLOW_RANGE, 100.0F)
        		.add(Attributes.ARMOR, 5.0F)
        		.add(Attributes.ARMOR_TOUGHNESS, 5.0F);
    }
    
    @Override
    protected void defineSynchedData() 
    {
    	super.defineSynchedData();
    	this.entityData.define(IS_RIDING, false);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }
    
    @Override
    public void tick() 
    {
    	super.tick();

		for(OrochiChain chain : this.chains)
		{
			chain.tick();
		}
		//this.chains.removeIf(t -> t.tickCount >= 100);
		
		if(this.hasTarget() && this.chains.isEmpty())
		{
			Vec2 rotation = new Vec2(0, this.yBodyRot);
			Vec3 lookPos = MultiverseUtil.getLookPos(rotation, this.getEyePosition(), 0, 0, -30);
			Vec3 targetPos = MultiverseUtil.getLookPos(rotation, this.getEyePosition(), 0, 0, 10);
			this.addChain(lookPos, targetPos, false);
		}
		
		if(!this.chains.isEmpty())
		{
			OrochiChain chain = this.chains.get(0);
			ChainSegment tip = chain.getSegments()[chain.getSegments().length - 3];
			Vec3 tipPos = tip.getPos();
			if(this.isRiding())
			{
				Vec2 tipRot = tip.getRot();
				Vec3 lookPos = MultiverseUtil.getLookPos(tipRot, tipPos, 0, 0.5F, 0);
				this.setPos(lookPos);
				this.setNoGravity(true);
				this.setXRot(tipRot.x);
				this.setYRot(tipRot.y);
				this.setYBodyRot(tipRot.y);
				this.setYHeadRot(tipRot.y);
			}
			if(this.position().distanceTo(tipPos) <= 3.5F)
			{
				this.setRiding(true);
			}
		}
    }
	
	@Override
	public boolean removeWhenFarAway(double p_21542_)
	{
		return false;
	}
	
	public void setRiding(boolean value)
	{
		this.entityData.set(IS_RIDING, value);
	}
	
	public boolean isRiding()
	{
		return this.entityData.get(IS_RIDING);
	}
    
	public void addChain(Vec3 pos, Vec3 target, boolean onGround)
	{
		Vec3 groundPos = onGround ? MultiverseUtil.getGroundPos(this.level, pos.x, pos.y, pos.z, 2) : pos;	
		OrochiChain chain = new OrochiChain(this, 100, 0.85F);
		for(ChainSegment segment : chain.getSegments())
		{
			segment.setPos(groundPos);
		}
		chain.setAnchorPos(groundPos);
		chain.setTarget(target);
		this.chains.add(chain);
	}
	
	public static class OrochiChain extends KinematicChain	
	{
		public final SmoothAnimationState jawOpenAnimationState = new SmoothAnimationState();
		public int tickCount;
		
		public OrochiChain(Entity entity, int length, float distance)
		{
			super(entity, length, distance);
		}
		
		@Override
		public void tick() 
		{
			this.setOldPosAndRot();
			super.tick();
			
			this.tickCount++;
			
			EntityOrochi orochi = (EntityOrochi) this.entity;
			ChainSegment tip = this.getTipSegment();
			
			if(this.target != null)
			{
				if(this.target.subtract(tip.getPos()).length() <= 2.0F)
				{
					Vec3 spreadPos = MultiverseUtil.getSpreadPosition(orochi.level, this.target, 10);
					this.setTarget(spreadPos);
				}
				if(orochi.level.isClientSide)
				{
					this.jawOpenAnimationState.updateWhen(this.target.subtract(tip.getPos()).length() <= 8.0F, this.tickCount);
				}
			}
			
			for(ChainSegment segment : this.segments)
			{
				Vec3 pos = segment.getPos();
	    		Vec3 size = new Vec3(0.5F, 0.5F, 0.5F);
	    		AABB aabb = new AABB(size.reverse(), size).move(pos);
	    		List<LivingEntity> list = orochi.level.getEntitiesOfClass(LivingEntity.class, aabb, target -> target != orochi && !target.isAlliedTo(orochi));
	    		list.forEach(target ->
	    		{
	    			if(orochi.doHurtTarget(target))
	    			{
	    				if(segment == tip)
	    				{
    	    				orochi.heal(5.0F);
	    				}
	    			}
	    		});
	    	}
		}
	}
}

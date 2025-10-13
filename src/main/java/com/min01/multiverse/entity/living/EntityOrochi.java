package com.min01.multiverse.entity.living;

import java.util.ArrayList;
import java.util.List;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.misc.KinematicChain;
import com.min01.multiverse.misc.KinematicChain.ChainSegment;
import com.min01.multiverse.network.MultiverseNetwork;
import com.min01.multiverse.network.UpdatePosArrayPacket;
import com.min01.multiverse.misc.SmoothAnimationState;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntityOrochi extends AbstractAnimatableMonster
{
	public final List<OrochiChain> chains = new ArrayList<>();
	
	public EntityOrochi(EntityType<? extends Monster> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
		this.posArray = new Vec3[100];
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
		
		if(this.hasTarget())
		{
			if(this.tickCount % 20 == 0 && this.posArray[0] != null)
			{
				this.addChain(this.position(), this.posArray[0].add(0, 30, 0));
			}
		}
    	
		if(this.getTarget() != null)
		{
			if(this.canMove())
			{
				//this.getNavigation().moveTo(this.getTarget(), 0.5F);
			}
			if(this.canLook() && this.getTarget().isAlive())
			{
        		this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
            	this.lookAt(this.getTarget(), 30.0F, 30.0F);
			}
			Vec3 targetPos = this.getTarget().position();
			this.posArray[0] = targetPos;
			MultiverseNetwork.sendToAll(new UpdatePosArrayPacket(this, targetPos, 0));
		}
    }
    
	public void addChain(Vec3 pos, Vec3 target)
	{
		Vec3 groundPos = MultiverseUtil.getGroundPos(this.level, pos.x, pos.y, pos.z, 2);	
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
			
			if(orochi.level.isClientSide && this.target != null)
			{
				this.jawOpenAnimationState.updateWhen(this.target.subtract(tip.getPos()).length() <= 4.5F, this.tickCount);
			}
			
			for(int i = 1; i < this.getSegments().length - 1; i++)
			{
				ChainSegment prev = this.getSegments()[i - 1];
				ChainSegment segment = this.getSegments()[i];
		        Vec3 toTarget = prev.getPos().subtract(segment.getPos());
		        double dist = toTarget.length();
		        double moveDist = Math.min(dist, 0.5F);
		        if(moveDist <= 0.1F)
		        {
		        	continue;
		        }
	    		Vec3 size = new Vec3(0.5F, 0.5F, 0.5F);
	    		AABB aabb = new AABB(size.reverse(), size).move(segment.getPos());
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

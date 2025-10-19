package com.min01.multiverse.entity.living;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.entity.living.EntityOrochi.OrochiChain.ChainType;
import com.min01.multiverse.misc.KinematicChain;
import com.min01.multiverse.misc.KinematicChain.ChainSegment;
import com.min01.multiverse.misc.SmoothAnimationState;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntityOrochi extends AbstractAnimatableMonster
{
	public static final EntityDataAccessor<Boolean> IS_INTRO = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.OPTIONAL_UUID);
	
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
    	this.entityData.define(IS_INTRO, false);
    	this.entityData.define(PHASE, 0);
    	this.entityData.define(TARGET_UUID, Optional.empty());
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
    }
    
    @Override
    public void tick() 
    {
    	super.tick();

		for(OrochiChain chain : this.chains)
		{
			chain.tick();
		}
		
		if(this.getPhase() == 1)
		{
			this.setCanLook(false);
			this.setNoGravity(true);
			this.setDeltaMovement(Vec3.ZERO);
			this.resetFallDistance();
		}
		
		if(this.getTarget() != null && this.getTarget().isAlive())
		{
			if(this.tickCount % 100 == 0 && this.getPhase() == 1)
			{
				for(int i = 0; i < 360; i += 45)
				{
					Vec2 rot = new Vec2(0, i);
					Vec3 lookPos = MultiverseUtil.getLookPos(rot, this.position(), 0, 0, 8);
					this.addChain(lookPos, lookPos.add(0, 10, 0), true, 30, 1.0F, ChainType.LASER);
				}
			}
			if(this.chains.isEmpty())
			{
				this.addChain(this.position().add(0, 150, 0), this.position().subtract(0, 300, 0), false, 30, 10.0F, ChainType.GIANT);
				this.setIntro(true);
			}
			else if(this.isIntro())
			{
				OrochiChain chain = this.chains.get(0);
				ChainSegment segment = chain.getSegments()[0];
				Vec3 pos = segment.getPos();
				if(pos.y <= this.getY() + 10)
				{
					this.setPhase(1);
					this.setIntro(false);
				}
				this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
				chain.setAnchorPos(null);
			}
			if(this.canLook())
			{
				this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
			}
		}
		else
		{
			if(!this.chains.isEmpty())
			{
				for(OrochiChain chain : new ArrayList<>(this.chains))
				{
					if(chain.chainType != ChainType.GIANT)
					{
						continue;
					}
					ChainSegment segment = chain.getSegments()[0];
					Vec3 pos = segment.getPos();
					if(pos.y >= this.getY())
					{
						Vec3 groundPos = MultiverseUtil.getGroundPos(this.level, this.getX(), this.getY(), this.getZ(), -1);
						this.setPhase(0);
						this.setIntro(false);
						this.setNoGravity(false);
						this.setCanLook(true);
						this.resetFallDistance();
						this.setPos(groundPos);
					}
					else if(pos.y <= this.level.getMinBuildHeight() - 100)
					{
						for(ChainSegment segments : chain.getSegments())
						{
							segments.setPos(this.position().subtract(0, 50, 0));
						}
					}
					else
					{
						chain.setAnchorPos(null);
					}
					if(chain.getTipSegment().getPos().y >= this.level.getMaxBuildHeight() - 100)
					{
						this.chains.clear();
					}
					chain.setTarget(this.position().add(0, 300, 0));
				}
			}
		}
    }
    
    @Override
    public void setTarget(LivingEntity p_21544_) 
    {
    	if(p_21544_ != null)
    	{
    		this.entityData.set(TARGET_UUID, Optional.of(p_21544_.getUUID()));
    	}
    	else
    	{
    		this.entityData.set(TARGET_UUID, Optional.empty());
    	}
    	super.setTarget(p_21544_);
    }
    
    @Override
    public LivingEntity getTarget() 
    {
    	if(this.level.isClientSide)
    	{
    		Optional<UUID> optional = this.entityData.get(TARGET_UUID);
    		if(optional.isPresent())
    		{
    			return MultiverseUtil.getEntityByUUID(this.level, optional.get());
    		}
    	}
    	return super.getTarget();
    }
    
    @Override
    public boolean isPickable() 
    {
    	return super.isPickable() && this.getPhase() != 1;
    }
    
    @Override
    public boolean displayFireAnimation() 
    {
    	return super.displayFireAnimation() && this.getPhase() != 1;
    }
	
	@Override
	public boolean hurt(DamageSource p_21016_, float p_21017_)
	{
		if(this.getPhase() == 1 && !p_21016_.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
		{
			return false;
		}
		return super.hurt(p_21016_, p_21017_);
	}
	
	@Override
	public boolean removeWhenFarAway(double p_21542_)
	{
		return false;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_21484_) 
	{
		super.addAdditionalSaveData(p_21484_);
		p_21484_.putBoolean("isIntro", this.isIntro());
		p_21484_.putInt("Phase", this.getPhase());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_21450_) 
	{
		super.readAdditionalSaveData(p_21450_);
		this.setIntro(p_21450_.getBoolean("isIntro"));
		this.setPhase(p_21450_.getInt("Phase"));
	}
	
	public void setPhase(int value)
	{
		this.entityData.set(PHASE, value);
	}
	
	public int getPhase()
	{
		return this.entityData.get(PHASE);
	}
	
	public void setIntro(boolean value)
	{
		this.entityData.set(IS_INTRO, value);
	}
	
	public boolean isIntro()
	{
		return this.entityData.get(IS_INTRO);
	}
    
	public void addChain(Vec3 pos, Vec3 target, boolean onGround, int length, float scale, ChainType type)
	{
		Vec3 groundPos = onGround ? MultiverseUtil.getGroundPos(this.level, pos.x, pos.y, pos.z, 2) : pos;	
		OrochiChain chain = new OrochiChain(this, length, 0.85F * scale, scale, type);
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
		public boolean openMouth;
		public boolean isLaser;
		public final float scale;
		public final ChainType chainType;
		
		public OrochiChain(Entity entity, int length, float distance, float scale, ChainType chainType)
		{
			super(entity, length, distance);
			this.scale = scale;
			this.chainType = chainType;
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
					if(this.chainType == ChainType.NORMAL)
					{
						this.generateNewTarget(orochi, 20, t -> this.setTarget(t));
					}
					else if(this.chainType == ChainType.LASER)
					{
						if(this.tickCount <= 200 && orochi.getTarget() != null && orochi.getTarget().isAlive())
						{
							this.openMouth = true;
							this.isLaser = true;
							tip.setPos(tip.getOldPos());
							this.setTarget(orochi.getTarget().getEyePosition());
						}
						else
						{
							this.openMouth = false;
							this.isLaser = false;
							this.setAnchorPos(null);
							this.setTarget(tip.getPos().subtract(0, 200, 0));
						}
					}
				}
			}
			
			if(this.isLaser)
			{
				List<LivingEntity> arrayList = new ArrayList<>();
	        	Vec3 startPos = MultiverseUtil.getLookPos(tip.getRot(), tip.getPos(), 0.0F, 0.0F, -0.85F);
				Vec3 lookPos = MultiverseUtil.getLookPos(tip.getRot(), startPos, 0.0F, 0.0F, 100.0F);
				HitResult hitResult = orochi.level.clip(new ClipContext(startPos, lookPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, orochi));
	        	Vec3 hitPos = hitResult.getLocation();
	            Vec3 targetPos = hitPos.subtract(startPos);
	            Vec3 normalizedPos = targetPos.normalize();
	            float dist = (float) startPos.distanceTo(hitPos);
	            for(int i = 1; i < dist; ++i)
	            {
	            	Vec3 rayPos = startPos.add(normalizedPos.scale(i));
	            	List<LivingEntity> list = orochi.level.getEntitiesOfClass(LivingEntity.class, new AABB(rayPos, rayPos).inflate(0.35F), t -> t != orochi && !t.isAlliedTo(orochi));
	            	list.forEach(t -> 
	            	{
	            		if(!arrayList.contains(t))
	            		{
	            			arrayList.add(t);
	            		}
	            	});
	            }
	            arrayList.forEach(t -> 
	            {
	            	t.hurt(orochi.damageSources().indirectMagic(orochi, orochi), 6.0F);
	            });
			}
			
			if(orochi.level.isClientSide)
			{
				this.jawOpenAnimationState.updateWhen(this.openMouth, this.tickCount);
			}
			
			for(ChainSegment segment : this.segments)
			{
				Vec3 pos = segment.getPos();
	    		Vec3 size = new Vec3(0.5F, 0.5F, 0.5F).scale(this.scale);
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
		
	    public void generateNewTarget(Entity entity, int radius, Consumer<Vec3> consumer) 
	    {
	        Level world = entity.level;
	        for(int i = 0; i < 10; i++)
	        {
	        	Vec3 pos = MultiverseUtil.getSpreadPosition(entity, radius);
	        	HitResult hitResult = world.clip(new ClipContext(entity.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
	        	if(hitResult instanceof BlockHitResult blockHit)
	        	{
	                BlockPos targetPos = blockHit.getBlockPos();
	                BlockState blockState = world.getBlockState(targetPos);
	                if(blockState.isAir())
	                {
	                	consumer.accept(blockHit.getLocation());
	                	break;
	                }
	        	}
	        }
	    }
	    
	    public static enum ChainType
	    {
	    	GIANT,
	    	LASER,
	    	NORMAL
	    }
	}
}

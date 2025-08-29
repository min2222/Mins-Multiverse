package com.min01.multiverse.entity;

import com.min01.multiverse.entity.ai.control.MultiverseSwimmingMoveControl;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAnimatableWaterAnimal extends WaterAnimal implements IAnimatable
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractAnimatableWaterAnimal.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractAnimatableWaterAnimal.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> CAN_LOOK = SynchedEntityData.defineId(AbstractAnimatableWaterAnimal.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> CAN_MOVE = SynchedEntityData.defineId(AbstractAnimatableWaterAnimal.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(AbstractAnimatableWaterAnimal.class, EntityDataSerializers.BOOLEAN);

	public Vec3[] posArray;
	
	public AbstractAnimatableWaterAnimal(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
		this.noCulling = true;
		this.moveControl = new MultiverseSwimmingMoveControl(this, 85, 0.5F, 0.1F, false);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}
	
	@Override
	protected void registerGoals() 
	{
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED), 40)
        {
        	@Override
        	public boolean canUse() 
        	{
        		return super.canUse() && AbstractAnimatableWaterAnimal.this.canRandomSwim();
        	}
        });
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ANIMATION_STATE, 0);
		this.entityData.define(ANIMATION_TICK, 0);
		this.entityData.define(CAN_LOOK, true);
		this.entityData.define(CAN_MOVE, true);
		this.entityData.define(HAS_TARGET, false);
	}
	
    @Override
    protected PathNavigation createNavigation(Level p_27480_) 
    {
    	return new WaterBoundPathNavigation(this, p_27480_);
    }
    
    @Override
    public void tick()
    {
    	super.tick();

		if(!this.level.isClientSide)
		{
			this.setHasTarget(this.getTarget() != null);
		}
		
		if(this.getAnimationTick() > 0)
		{
			this.setAnimationTick(this.getAnimationTick() - 1);
		}
    }
    
	public static boolean checkFishSpawnRules(EntityType<? extends AbstractAnimatableWaterAnimal> type, ServerLevelAccessor pServerLevel, MobSpawnType pMobSpawnType, BlockPos pPos, RandomSource pRandom) 
    {
		return pServerLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pServerLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }
    
    @Override
    public void travel(Vec3 p_27490_) 
    {
    	if(this.isEffectiveAi() && this.isInWater())
    	{
    		this.moveRelative(this.getSpeed(), p_27490_);
    		this.move(MoverType.SELF, this.getDeltaMovement());
    		this.setDeltaMovement(this.getDeltaMovement().scale(0.9F));
    	}
    	else
    	{
    		super.travel(p_27490_);
    	}
    }
	
	@Override
	public void lookAt(Anchor p_20033_, Vec3 p_20034_)
	{
		Vec3 vec3 = p_20033_.apply(this);
		double d0 = p_20034_.x - vec3.x;
		double d1 = p_20034_.y - vec3.y;
		double d2 = p_20034_.z - vec3.z;
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		float yRot = (float)(Mth.atan2(d2, d0) * (double)(180.0F / (float)Math.PI)) - 90.0F;
		this.setXRot(Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * (double)(180.0F / (float)Math.PI)))));
		this.setYRot(MultiverseUtil.rotlerp(this.getYRot(), yRot, (float)this.getBodyRotationSpeed()));
		this.setYHeadRot(this.getYRot());
		this.xRotO = this.getXRot();
		this.yRotO = this.getYRot();
		this.yHeadRotO = this.yHeadRot;
		this.yBodyRot = this.yHeadRot;
		this.yBodyRotO = this.yBodyRot;
	}
	
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	this.setCanLook(p_21450_.getBoolean("CanLook"));
    	this.setCanMove(p_21450_.getBoolean("CanMove"));
    	this.setAnimationTick(p_21450_.getInt("AnimationTick"));
    	this.setAnimationState(p_21450_.getInt("AnimationState"));
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) 
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putBoolean("CanLook", this.canLook());
    	p_21484_.putBoolean("CanMove", this.canMove());
    	p_21484_.putInt("AnimationTick", this.getAnimationTick());
    	p_21484_.putInt("AnimationState", this.getAnimationState());
    }
	
	public int getBodyRotationSpeed()
	{
		return 10;
	}
	
	public boolean canRandomSwim()
	{
		return (!this.isUsingSkill() || this.getTarget() == null) && this.getNavigation().isDone();
	}
	
	public void setHasTarget(boolean value)
	{
		this.entityData.set(HAS_TARGET, value);
	}
	
	public boolean hasTarget()
	{
		return this.entityData.get(HAS_TARGET);
	}
	
	@Override
	public boolean isUsingSkill() 
	{
		return this.getAnimationTick() > 0;
	}
	
    public void setCanLook(boolean value)
    {
    	this.entityData.set(CAN_LOOK, value);
    }
    
    public boolean canLook()
    {
    	return this.entityData.get(CAN_LOOK);
    }
    
    @Override
    public void setCanMove(boolean value)
    {
    	this.entityData.set(CAN_MOVE, value);
    }
    
    @Override
    public boolean canMove()
    {
    	return this.entityData.get(CAN_MOVE);
    }
    
    @Override
    public void setAnimationTick(int value)
    {
        this.entityData.set(ANIMATION_TICK, value);
    }
    
    @Override
    public int getAnimationTick()
    {
        return this.entityData.get(ANIMATION_TICK);
    }
    
    public void setAnimationState(int value)
    {
        this.entityData.set(ANIMATION_STATE, value);
    }
    
    public int getAnimationState()
    {
    	if(!this.isUsingSkill())
    	{
    		return 0;
    	}
        return this.entityData.get(ANIMATION_STATE);
    }
}

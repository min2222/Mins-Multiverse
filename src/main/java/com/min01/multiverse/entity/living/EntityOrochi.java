package com.min01.multiverse.entity.living;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.entity.ai.goal.OrochiDodgeGoal;
import com.min01.multiverse.entity.ai.goal.OrochiLaserGoal;
import com.min01.multiverse.misc.KinematicChain;
import com.min01.multiverse.misc.KinematicChain.ChainSegment;
import com.min01.multiverse.misc.MultiverseEntityDataSerializers;
import com.min01.multiverse.misc.SmoothAnimationState;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntityOrochi extends AbstractAnimatableMonster
{
	public static final EntityDataAccessor<Boolean> IS_ANIM = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_LASER = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_SNAKE = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> CHAIN_TYPE = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.FLOAT);
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.defineId(EntityOrochi.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Vec3> WANTED_POS = SynchedEntityData.defineId(EntityOrochi.class, MultiverseEntityDataSerializers.VEC3.get());
	public static final EntityDataAccessor<Vec3> ANCHOR_POS = SynchedEntityData.defineId(EntityOrochi.class, MultiverseEntityDataSerializers.VEC3.get());

	public final SmoothAnimationState jawOpenAnimationState = new SmoothAnimationState();
	
	public KinematicChain chain;
	
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
    	this.entityData.define(IS_ANIM, false);
    	this.entityData.define(IS_LASER, false);
    	this.entityData.define(IS_SNAKE, false);
    	this.entityData.define(CHAIN_TYPE, 0);
    	this.entityData.define(SCALE, 1.0F);
    	this.entityData.define(TARGET_UUID, Optional.empty());
    	this.entityData.define(OWNER_UUID, Optional.empty());
    	this.entityData.define(WANTED_POS, Vec3.ZERO);
    	this.entityData.define(ANCHOR_POS, Vec3.ZERO);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(0, new OrochiDodgeGoal(this));
    	this.goalSelector.addGoal(0, new OrochiLaserGoal(this));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, t -> !(t instanceof EntityOrochi)));
    }
    
    @Override
    public void tick() 
    {
    	super.tick();

    	if(this.isSnake())
    	{
    		this.resetFallDistance();
    		this.setNoGravity(true);
    		this.refreshDimensions();
    		if(this.chain == null)
    		{
    			this.chain = new KinematicChain(this, 30, 0.85F * this.getScale());
    		}
    		else
    		{
    			this.chain.setOldPosAndRot();
    			this.chain.tick();
    			this.chain.setTarget(this.getWantedPos());
    			this.chain.setAnchorPos(this.getAnchorPos());

    			ChainSegment tip = this.chain.getLastSegment();
    			Vec3 firstPos = this.chain.getSegments()[0].getPos();
    			Vec3 tipPos = tip.getPos();
    			Vec2 rot = tip.getRot();
    			
    			this.setPos(tipPos);
    			this.setXRot(rot.x);
    			this.setYRot(rot.y);
    			this.setYBodyRot(rot.y);
    			this.setYHeadRot(rot.y);

    			this.xRotO = rot.x;
    			this.yRotO = rot.y;
    			this.yHeadRotO = rot.y;
    			this.yBodyRotO = rot.y;
    			
        		if(firstPos.y < -128 && this.chain.getAnchorPos() == null)
        		{
        			this.discard();
        		}
    			
    			if(this.getChainType() == ChainType.LASER)
    			{
    				if(this.isAnim() && this.isReached())
    				{
    					this.chain.setTarget(Vec3.ZERO);
    				}
    				if(this.isLaser())
    				{
    					List<LivingEntity> arrayList = new ArrayList<>();
			        	Vec3 startPos = tip.getPos();
						Vec3 lookPos = MultiverseUtil.getLookPos(tip.getRot(), startPos, 0.0F, 0.0F, 200.0F);
						HitResult hitResult = this.level.clip(new ClipContext(startPos, lookPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
			        	Vec3 hitPos = hitResult.getLocation();
			            Vec3 targetPos = hitPos.subtract(startPos);
			            Vec3 normalizedPos = targetPos.normalize();
			            for(int i = 1; i < Mth.floor(targetPos.length()); ++i)
			            {
			            	Vec3 rayPos = startPos.add(normalizedPos.scale(i));
			            	List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, new AABB(rayPos, rayPos).inflate(0.25F * this.getScale()), t -> t != this && !t.isAlliedTo(this) && !(t instanceof EntityOrochi));
			            	arrayList.addAll(list);
			            }
			            arrayList.forEach(t -> 
			            {
			            	if(t.hurt(this.damageSources().indirectMagic(this, this), 15.0F))
			            	{
			            		if(this.getOwner() != null)
			            		{
	    	    					this.getOwner().heal(15.0F);
			            		}
			            	}
			            });
    				}
    			}

    			boolean flag = this.getOwner() instanceof EntityOrochi ? true : this.getChainType() != ChainType.CARRY;
    			if(flag)
    			{
        			for(ChainSegment segment : this.chain.getSegments())
        			{
        				Vec3 pos = segment.getPos();
        	    		Vec3 size = new Vec3(0.5F, 0.5F, 0.5F).scale(this.getScale());
        	    		AABB aabb = new AABB(size.reverse(), size).move(pos);
        	    		List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, aabb, target -> target != this.getOwner() && target != this && !target.isAlliedTo(this) && !(target instanceof EntityOrochi));
        	    		list.forEach(target ->
        	    		{
        	    			this.doHurtTarget(target);
        	    		});
        	    	}
    			}
    		}

			if(this.getOwner() instanceof EntityOrochi orochi)
			{
    			if(orochi.getTarget() != null)
    			{
    				this.setTarget(orochi.getTarget());
    			}
    			if(this.getChainType() == ChainType.CARRY)
    			{
    				this.setAnchorPos(null);
    				if(!this.isVehicle())
    				{
    					if(!this.isReached())
    					{
    						if(this.getWantedPos().y > this.level.getMinBuildHeight())
    						{
                				this.setWantedPos(orochi.position());
    						}
    					}
    					else
        				{
    						orochi.startRiding(this);
        					this.setWantedPos(Vec3.ZERO);
        				}
    				}
    				else
    				{
    					if(this.getWantedPos().equals(Vec3.ZERO))
    					{
    						Vec3 pos = MultiverseUtil.getLookPos(new Vec2(0, orochi.getYHeadRot()), orochi.position(), 0, 0, -20);
        					this.setWantedPos(MultiverseUtil.getGroundPos(this.level, pos.x, pos.y, pos.z, -1));
    					}
    					else if(this.isReached())
    					{
    						orochi.stopRiding();
    						this.setWantedPos(this.position().subtract(0, 200, 0));
    					}
    				}
    			}
			}
			else if(this.getOwner() != null)
    		{
				Entity entity = this.getOwner();
    			if(this.getChainType() == ChainType.CARRY)
    			{
    				this.setAnchorPos(null);
    				if(!this.isVehicle())
    				{
    					if(!this.isReached())
    					{
    						if(this.getWantedPos().y > this.level.getMinBuildHeight())
    						{
                				this.setWantedPos(entity.position());
    						}
    					}
    					else
        				{
    						entity.startRiding(this);
        					this.setWantedPos(Vec3.ZERO);
        				}
    				}
    				else
    				{
						Vec3 pos = MultiverseUtil.getLookPos(new Vec2(entity.getXRot(), entity.getYHeadRot()), this.position(), 0, 0, 5);
						HitResult hitResult = this.level.clip(new ClipContext(entity.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
    					this.setWantedPos(hitResult.getLocation());
    				}
    			}
    			if(this.getChainType() == ChainType.SPIRIT)
    			{
    				this.setAnchorPos(null);
    				if(this.tickCount >= 200 || this.isReached())
    				{
    					this.setWantedPos(this.position().subtract(0, 200, 0));
    				}
    			}
    		}
    		
    		if(this.level.isClientSide)
    		{
    			this.jawOpenAnimationState.updateWhen(this.isAnim(), this.tickCount);
    		}
    	}
    	else if(this.getTarget() != null)
    	{
    		if(this.canLook() && !this.level.isClientSide)
    		{
    			this.getLookControl().setLookAt(this.getTarget(), 100.0F, 100.0F);
    		}
    	}
    }
    
    @Override
    public EntityDimensions getDimensions(Pose p_21047_)
    {
    	if(this.isSnake())
    	{
    		return EntityDimensions.fixed(this.getScale(), this.getScale());
    	}
    	return super.getDimensions(p_21047_);
    }
    
    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_)
    {
    	if(this.isSnake() && !p_21016_.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
    	{
    		return false;
    	}
    	return super.hurt(p_21016_, p_21017_);
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
	public boolean removeWhenFarAway(double p_21542_)
	{
		return false;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_21484_) 
	{
		super.addAdditionalSaveData(p_21484_);
		p_21484_.putBoolean("isAnim", this.isAnim());
		p_21484_.putBoolean("isLaser", this.isLaser());
		p_21484_.putBoolean("isSnake", this.isSnake());
		p_21484_.putInt("ChainType", this.getChainType().ordinal());
		p_21484_.putFloat("Scale", this.getScale());
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			p_21484_.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_21450_) 
	{
		super.readAdditionalSaveData(p_21450_);
		this.setAnim(p_21450_.getBoolean("isAnim"));
		this.setLaser(p_21450_.getBoolean("isLaser"));
		this.setSnake(p_21450_.getBoolean("isSnake"));
		this.setChainType(ChainType.values()[p_21450_.getInt("ChainType")]);
		this.setScale(p_21450_.getFloat("Scale"));
		if(p_21450_.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_21450_.getUUID("Owner")));
		}
	}
	
	public void setAnim(boolean value)
	{
		this.entityData.set(IS_ANIM, value);
	}
	
	public boolean isAnim()
	{
		return this.entityData.get(IS_ANIM);
	}
	
	public void setLaser(boolean value)
	{
		this.entityData.set(IS_LASER, value);
	}
	
	public boolean isLaser()
	{
		return this.entityData.get(IS_LASER);
	}
    
	public void setSnake(boolean value)
	{
		this.entityData.set(IS_SNAKE, value);
	}
	
	public boolean isSnake()
	{
		return this.entityData.get(IS_SNAKE);
	}
	
	public void setChainType(ChainType value)
	{
		this.entityData.set(CHAIN_TYPE, value.ordinal());
	}
	
	public ChainType getChainType()
	{
		return ChainType.values()[this.entityData.get(CHAIN_TYPE)];
	}
	
	public void setScale(float value)
	{
		this.entityData.set(SCALE, value);
	}
	
	public float getScale()
	{
		return this.entityData.get(SCALE);
	}
	
	public void setWantedPos(Vec3 pos)
	{
		this.entityData.set(WANTED_POS, pos);
	}
	
	public Vec3 getWantedPos()
	{
		return this.entityData.get(WANTED_POS);
	}
	
	public void setAnchorPos(Vec3 pos)
	{
		this.entityData.set(ANCHOR_POS, pos);
	}
	
	public Vec3 getAnchorPos()
	{
		return this.entityData.get(ANCHOR_POS);
	}
	
	public void setOwner(LivingEntity owner)
	{
		this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
	}
	
	@Nullable
	public LivingEntity getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return MultiverseUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
	
	public void addChain(Vec3 pos, Vec3 target, boolean onGround, float scale, ChainType type)
	{
		Vec3 groundPos = onGround ? MultiverseUtil.getGroundPos(this.level, pos.x, pos.y, pos.z, 2) : pos;	
		EntityOrochi orochi = new EntityOrochi(MultiverseEntities.OROCHI.get(), this.level);
		orochi.setPos(groundPos);
		orochi.setSnake(true);
		orochi.setScale(scale);
		orochi.setChainType(type);
		orochi.setWantedPos(target);
		orochi.setAnchorPos(groundPos);
		orochi.setOwner(this);
		if(this.getTarget() != null)
		{
			orochi.setTarget(this.getTarget());
		}
		this.level.addFreshEntity(orochi);
	}
	
	public boolean isReached()
	{
		if(this.chain != null)
		{
			return this.position().distanceTo(this.chain.getTarget()) <= this.getScale() * 2.5F;
		}
		return false;
	}
	
    public static enum ChainType
    {
    	GIANT,
    	LASER,
    	CARRY,
    	SPIRIT,
    	NORMAL
    }
}

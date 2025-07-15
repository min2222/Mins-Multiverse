package com.min01.multiverse.entity.projectile;

import com.min01.multiverse.entity.EntityCameraShake;
import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.misc.MultiverseEntityDataSerializers;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class EntityScarletMagic extends ThrowableProjectile
{
	public static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityScarletMagic.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Vec3> GATE_POS = SynchedEntityData.defineId(EntityScarletMagic.class, MultiverseEntityDataSerializers.VEC3.get());
	public static final EntityDataAccessor<Vec3> ROTATION = SynchedEntityData.defineId(EntityScarletMagic.class, MultiverseEntityDataSerializers.VEC3.get());
	public static final EntityDataAccessor<Vec3> TARGET_ROT = SynchedEntityData.defineId(EntityScarletMagic.class, MultiverseEntityDataSerializers.VEC3.get());

	public EntityScarletMagic(EntityType<? extends EntityScarletMagic> p_37391_, Level p_37392_) 
	{
		super(p_37391_, p_37392_);
		this.setNoGravity(true);
	}

	public EntityScarletMagic(Level p_37399_, LivingEntity p_37400_) 
	{
		super(MultiverseEntities.SCARLET_MAGIC.get(), p_37400_, p_37399_);
		this.setNoGravity(true);
	}

	public EntityScarletMagic(Level p_37394_, double p_37395_, double p_37396_, double p_37397_)
	{
		super(MultiverseEntities.SCARLET_MAGIC.get(), p_37395_, p_37396_, p_37397_, p_37394_);
		this.setNoGravity(true);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		this.entityData.define(TYPE, 0);
		this.entityData.define(GATE_POS, Vec3.ZERO);
		this.entityData.define(ROTATION, Vec3.ZERO);
		this.entityData.define(TARGET_ROT, Vec3.ZERO);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		ScarletMagicType type = this.getScarletMagicType();
		Mob owner = (Mob) this.getOwner();
		this.noCulling = type == ScarletMagicType.HAND;
		if(type == ScarletMagicType.GATE)
		{
			if(owner != null)
			{
				if(this.tickCount == 50)
				{
					EntityScarletMagic hand = new EntityScarletMagic(this.level, owner);
					hand.setPos(this.position());
					hand.setRotation(this.getRotation());
					hand.setGatePos(this.position());
					hand.shootFromRotation(owner, 90.0F, (float) hand.getRotation().y, 0.0F, 5.0F, 0.0F);
					hand.setScarletMagicType(ScarletMagicType.HAND);
					this.level.addFreshEntity(hand);
				}
			}
			this.setDeltaMovement(Vec3.ZERO);
		}
		if(type == ScarletMagicType.HAND)
		{
			Vec3 vec3 = this.getDeltaMovement();
			if(this.xRotO == 0.0F && this.yRotO == 0.0F) 
			{
				double d0 = vec3.horizontalDistance();
				this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180.0F / (float)Math.PI)));
				this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180.0F / (float)Math.PI)));
				this.yRotO = this.getYRot();
				this.xRotO = this.getXRot();
			}
			if(this.getGatePos().subtract(this.position()).length() <= 0.5F)
			{
				this.discard();
			}
		}
		if(this.tickCount >= type.lifetime)
		{
			this.discard();
		}
	}

	@Override
	public void lerpTo(double p_36728_, double p_36729_, double p_36730_, float p_36731_, float p_36732_, int p_36733_, boolean p_36734_) 
	{
		this.setPos(p_36728_, p_36729_, p_36730_);
		this.setRot(p_36731_, p_36732_);
	}
	
	@Override
	protected void updateRotation() 
	{
		
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37404_)
	{
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		ScarletMagicType type = this.getScarletMagicType();
		if(entity != this.getOwner() && type.proj && this.getOwner() != null && !entity.isAlliedTo(this.getOwner()))
		{
			
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult p_37258_) 
	{
		super.onHitBlock(p_37258_);
		ScarletMagicType type = this.getScarletMagicType();
		if(type == ScarletMagicType.HAND)
		{
			this.setPos(p_37258_.getLocation());
			this.setDeltaMovement(MultiverseUtil.fromToVector(this.position(), this.getGatePos(), 3.0F));
			this.setOnGround(true);
			EntityCameraShake.cameraShake(this.level, this.position(), 50.0F, 0.35F, 0, 10);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37449_)
	{
		super.addAdditionalSaveData(p_37449_);
		p_37449_.putInt("ScarletMagicType", this.getScarletMagicType().ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37445_)
	{
		super.readAdditionalSaveData(p_37445_);
		if(p_37445_.contains("ScarletMagicType"))
		{
			this.setScarletMagicType(ScarletMagicType.values()[p_37445_.getInt("ScarletMagicType")]);
		}
	}
	
	public static enum ScarletMagicType
	{
		GATE(150, 10.0F, false),
		HAND(100, 1.0F, false);
		
		private int lifetime;
		private float scale;
		private boolean proj;
		
		private ScarletMagicType(int lifetime, float scale, boolean proj) 
		{
			this.lifetime = lifetime;
			this.scale = scale;
			this.proj = proj;
		}
		
		public boolean isProj()
		{
			return this.proj;
		}
		
		public float getScale()
		{
			return this.scale;
		}
	}
	
	public void setGatePos(Vec3 vec3)
	{
		this.entityData.set(GATE_POS, vec3);
	}
	
	public Vec3 getGatePos()
	{
		return this.entityData.get(GATE_POS);
	}

	public void setTargetRotation(Vec3 vec3)
	{
		this.entityData.set(TARGET_ROT, vec3);
	}
	
	public Vec3 getTargetRotation()
	{
		return this.entityData.get(TARGET_ROT);
	}
	
	public void setRotation(Vec3 vec3)
	{
		this.entityData.set(ROTATION, vec3);
	}
	
	public Vec3 getRotation()
	{
		return this.entityData.get(ROTATION);
	}
	
	public void setScarletMagicType(ScarletMagicType value)
	{
		this.entityData.set(TYPE, value.ordinal());
	}
	
	public ScarletMagicType getScarletMagicType()
	{
		return ScarletMagicType.values()[this.entityData.get(TYPE)];
	}
}

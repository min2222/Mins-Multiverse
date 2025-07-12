package com.min01.multiverse.entity.projectile;

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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntitySin extends ThrowableProjectile
{
	public static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntitySin.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> SWORD_TYPE = SynchedEntityData.defineId(EntitySin.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Vec3> ROTATION = SynchedEntityData.defineId(EntitySin.class, MultiverseEntityDataSerializers.VEC3.get());
	public static final EntityDataAccessor<Vec3> TARGET_ROT = SynchedEntityData.defineId(EntitySin.class, MultiverseEntityDataSerializers.VEC3.get());

	public EntitySin(EntityType<? extends EntitySin> p_37391_, Level p_37392_) 
	{
		super(p_37391_, p_37392_);
		this.setNoGravity(true);
	}

	public EntitySin(Level p_37399_, LivingEntity p_37400_) 
	{
		super(MultiverseEntities.SIN.get(), p_37400_, p_37399_);
		this.setNoGravity(true);
	}

	public EntitySin(Level p_37394_, double p_37395_, double p_37396_, double p_37397_)
	{
		super(MultiverseEntities.SIN.get(), p_37395_, p_37396_, p_37397_, p_37394_);
		this.setNoGravity(true);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		this.entityData.define(TYPE, 0);
		this.entityData.define(SWORD_TYPE, 0);
		this.entityData.define(ROTATION, Vec3.ZERO);
		this.entityData.define(TARGET_ROT, Vec3.ZERO);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if(this.getSwordType() == 0)
		{
			int number = (int)(Math.random() * 24) + 1;
			this.setSwordType(number);
		}
		SinType type = this.getSinType();
		Mob owner = (Mob) this.getOwner();
		if(type == SinType.GATE)
		{
			if(owner != null)
			{
				if(this.tickCount == 20)
				{
					Vec3 rotation = this.getRotation();
					Vec2 rot = new Vec2((float)rotation.x, (float)rotation.y);
					Vec3 lookPos = MultiverseUtil.getLookPos(rot, this.position(), 0, 0, 10);
					EntitySin spear = new EntitySin(this.level, owner);
					spear.setPos(this.position());
					spear.setDeltaMovement(MultiverseUtil.fromToVector(this.position(), lookPos, 1.5F));
					spear.setSinType(SinType.GATE_SWORD);
					this.level.addFreshEntity(spear);
				}
			}
			this.setDeltaMovement(Vec3.ZERO);
		}
		if(type == SinType.GATE_SWORD)
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
		}
		if(type == SinType.BLOCKING)
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
			if(this.tickCount >= 20 && !this.onGround())
			{
				float time = Math.min(1.0F, this.tickCount * 0.01F);
				Vec3 targetRot = this.getTargetRotation();
				Vec3 rot = this.getRotation();
				this.setRotation(rot.lerp(targetRot, time));
				if(Mth.equal(rot.x, targetRot.x))
				{
					Vec3 lookPos = MultiverseUtil.getLookPos(new Vec2((float) targetRot.x, (float) targetRot.y), this.position(), 0, 0, 10);
					this.setDeltaMovement(MultiverseUtil.fromToVector(this.position(), lookPos, 1.5F));
				}
			}
		}
		if(this.tickCount >= type.lifetime)
		{
			this.discard();
		}
	}
    
    @Override
    public boolean isPickable() 
    {
    	return this.getSinType() == SinType.GATE_SWORD;
    }

	@Override
	public void lerpTo(double p_36728_, double p_36729_, double p_36730_, float p_36731_, float p_36732_, int p_36733_, boolean p_36734_) 
	{
		this.setPos(p_36728_, p_36729_, p_36730_);
		this.setRot(p_36731_, p_36732_);
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return this.getSinType() == SinType.GATE_SWORD;
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
		SinType type = this.getSinType();
		if(entity != this.getOwner() && type.proj && this.getOwner() != null && !entity.isAlliedTo(this.getOwner()))
		{
			boolean flag = entity instanceof Player player ? !player.getAbilities().instabuild : true;
			if(entity.hurt(this.damageSources().indirectMagic(this, this.getOwner()), 20.0F))
			{
				entity.invulnerableTime = 0;
			}
			if(flag && entity instanceof LivingEntity)
			{
				entity.addDeltaMovement(MultiverseUtil.fromToVector(this.position(), entity.position(), 1.5F));
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult p_37258_) 
	{
		super.onHitBlock(p_37258_);
		SinType type = this.getSinType();
		if(type.proj)
		{
			this.setPos(p_37258_.getLocation());
			this.setDeltaMovement(Vec3.ZERO);
			this.setOnGround(true);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37449_)
	{
		super.addAdditionalSaveData(p_37449_);
		p_37449_.putInt("SinType", this.getSinType().ordinal());
		p_37449_.putInt("SwordType", this.getSwordType());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37445_)
	{
		super.readAdditionalSaveData(p_37445_);
		if(p_37445_.contains("SinType"))
		{
			this.setSinType(SinType.values()[p_37445_.getInt("SinType")]);
		}
		if(p_37445_.contains("SwordType"))
		{
			this.setSwordType(p_37445_.getInt("SwordType"));
		}
	}
	
	public static enum SinType
	{
		GATE(50, 15.0F, false),
		GATE_SWORD(100, 0.5F, true),
		BLOCKING(200, 0.5F, true);
		
		private int lifetime;
		private float scale;
		private boolean proj;
		
		private SinType(int lifetime, float scale, boolean proj) 
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
	
	public void setSwordType(int value)
	{
		this.entityData.set(SWORD_TYPE, value);
	}
	
	public int getSwordType()
	{
		return this.entityData.get(SWORD_TYPE);
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
	
	public void setSinType(SinType value)
	{
		this.entityData.set(TYPE, value.ordinal());
	}
	
	public SinType getSinType()
	{
		return SinType.values()[this.entityData.get(TYPE)];
	}
}

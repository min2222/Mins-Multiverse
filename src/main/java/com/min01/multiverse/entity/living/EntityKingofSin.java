package com.min01.multiverse.entity.living;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.entity.ai.goal.AbstractAnimationSkillGoal;
import com.min01.multiverse.entity.ai.goal.KingofSinGateGoal;
import com.min01.multiverse.entity.ai.goal.KingofSinSwordBarrageGoal;
import com.min01.multiverse.entity.projectile.EntitySin;
import com.min01.multiverse.entity.projectile.EntitySin.SinType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntityKingofSin extends AbstractAnimatableMonster
{
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState crossAnimationState = new AnimationState();
	
	public Class<? extends AbstractAnimationSkillGoal<?>> goal;
	
	public EntityKingofSin(EntityType<? extends Monster> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 350.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.35F)
        		.add(Attributes.ATTACK_DAMAGE, 35.0F)
        		.add(Attributes.FOLLOW_RANGE, 100.0F)
        		.add(Attributes.KNOCKBACK_RESISTANCE, 100.0F)
        		.add(Attributes.ARMOR, 30.0F)
        		.add(Attributes.ARMOR_TOUGHNESS, 30.0F);
    }
    
    @Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new KingofSinGateGoal(this));
		this.goalSelector.addGoal(0, new KingofSinSwordBarrageGoal(this));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.5D));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	this.resetFallDistance();
    	if(this.level.isClientSide)
    	{
    		this.crossAnimationState.startIfStopped(this.tickCount);
    		this.idleAnimationState.animateWhen(this.getAnimationState() == 0 && !MultiverseUtil.isMoving(this), this.tickCount);
    	}
    	if(this.getTarget() != null)
    	{
    		if(this.canLook())
    		{
        		this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
            	this.lookAt(this.getTarget(), 30.0F, 30.0F);
    		}
    		List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(2), t -> t != this && !(t instanceof LivingEntity) && !(t instanceof EntitySin) && !t.isAlliedTo(this));
    		list.forEach(t ->
    		{
    			Pair<Boolean, Vec3> dodge = MultiverseUtil.getDodge(t, this);
    			if(dodge.getLeft())
    			{
					boolean flag = t instanceof Projectile proj && proj.getOwner() != null ? !proj.getOwner().isAlliedTo(this) : true;
					if(flag)
					{
        				EntitySin sin = new EntitySin(this.level, this);
        				Vec2 rot = MultiverseUtil.lookAt(this.getEyePosition(), t.position());
        				Vec2 lookAt = MultiverseUtil.lookAt(this.getTarget().getEyePosition(), t.position());
        				sin.setPos(t.position());
        				sin.setSinType(SinType.BLOCKING);
        				sin.setDeltaMovement(Vec3.ZERO);
        				sin.setRotation(new Vec3(90, rot.y, 0));
        				sin.setTargetRotation(new Vec3(lookAt.x, rot.y, 0));
        				this.level.addFreshEntity(sin);
    					t.discard();
					}
    			}
    		});
    	}
    	if(!this.level.isClientSide)
    	{
    		//this.level.broadcastEntityEvent(this, (byte) 99);
    	}
    }
    
    @Override
    protected void updateWalkAnimation(float p_268283_) 
    {
        float f = Math.min(p_268283_ * 6.0F, 1.0F);
        this.walkAnimation.update(f, 0.4F);
    }
    
    @Override
    public void handleEntityEvent(byte p_21375_)
    {
    	super.handleEntityEvent(p_21375_);
    	if(p_21375_ == 99)
    	{
    		for(int i = 0; i < 10; i++)
    		{
        		Vec3 pos = MultiverseUtil.randomPointAroundBox(this.getBoundingBox(), this.random, 1.0F);
        		this.level.addAlwaysVisibleParticle(ParticleTypes.LARGE_SMOKE, pos.x, pos.y, pos.z, 0, 0, 0);
    		}
    	}
    }
    
    @Override
    public boolean removeWhenFarAway(double p_21542_) 
    {
    	return false;
    }
    
    @Override
    public boolean canBeAffected(MobEffectInstance p_21197_) 
    {
        MobEffect effect = p_21197_.getEffect();
    	return effect != MobEffects.WITHER;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) 
    {
    	return SoundEvents.WITHER_HURT;
    }
    
    @Override
    public void push(double p_20286_, double p_20287_, double p_20288_)
    {
    	super.push(p_20286_, p_20287_, p_20288_);
    }
}

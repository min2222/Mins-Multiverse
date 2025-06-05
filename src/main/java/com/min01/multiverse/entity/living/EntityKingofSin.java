package com.min01.multiverse.entity.living;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityKingofSin extends AbstractAnimatableMonster
{
	public final AnimationState idleAnimationState = new AnimationState();
	
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
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.5D));
    	this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    	this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	if(this.level.isClientSide)
    	{
    		this.idleAnimationState.animateWhen(this.getAnimationState() == 0 && !MultiverseUtil.isMoving(this), this.tickCount);
    	}
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) 
    {
    	return SoundEvents.WITHER_HURT;
    }
}

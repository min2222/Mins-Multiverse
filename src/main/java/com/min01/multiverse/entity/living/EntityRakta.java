package com.min01.multiverse.entity.living;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.entity.ai.goal.AbstractAnimationSkillGoal;
import com.min01.multiverse.entity.ai.goal.RaktaScarletMagicGoal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityRakta extends AbstractAnimatableMonster
{
	/*하늘위에 적 방향으로 일직선 포탈을 생성, 손이 순서대로 나오면서 공격
	체력이 적을시 :
		포탈에서 나온 손에 탑승
		주변에 포탈을 여러개 생성하고, 거기서 손이 하나씩 나옴
		그리고 손이 뭘 할지는 생각중*/
	public Class<? extends AbstractAnimationSkillGoal<?>> goal;
	
	public EntityRakta(EntityType<? extends Monster> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 500.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.25F)
        		.add(Attributes.ATTACK_DAMAGE, 65.0F)
        		.add(Attributes.FOLLOW_RANGE, 100.0F)
        		.add(Attributes.KNOCKBACK_RESISTANCE, 100.0F)
        		.add(Attributes.ARMOR, 10.0F)
        		.add(Attributes.ARMOR_TOUGHNESS, 10.0F);
    }
    
    @Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new RaktaScarletMagicGoal(this));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.5D));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
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
    public float getVoicePitch()
    {
    	return 0.2F;
    }
}
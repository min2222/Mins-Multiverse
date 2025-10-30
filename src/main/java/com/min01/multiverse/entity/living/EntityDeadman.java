package com.min01.multiverse.entity.living;

import com.min01.multiverse.entity.AbstractAnimatableMonster;
import com.min01.multiverse.entity.ai.goal.AbstractAnimationSkillGoal;
import com.min01.multiverse.entity.ai.goal.DeadmanBackDashGoal;
import com.min01.multiverse.entity.ai.goal.DeadmanDashGoal;
import com.min01.multiverse.entity.ai.goal.DeadmanJumpGoal;
import com.min01.multiverse.misc.SmoothAnimationState;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class EntityDeadman extends AbstractAnimatableMonster
{
	public final SmoothAnimationState dashAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState blockAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState jumpAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState smashAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState backdashAnimationState = new SmoothAnimationState();
	
	public Class<? extends AbstractAnimationSkillGoal<EntityDeadman>> goal;
	
	public EntityDeadman(EntityType<? extends Monster> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 100.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.35F)
        		.add(Attributes.ATTACK_DAMAGE, 12.0F)
        		.add(Attributes.FOLLOW_RANGE, 50.0F);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(0, new DeadmanBackDashGoal(this));
    	this.goalSelector.addGoal(0, new DeadmanDashGoal(this));
    	this.goalSelector.addGoal(0, new DeadmanJumpGoal(this));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, t -> !(t instanceof EntityDeadman)));
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	
    	if(this.level.isClientSide)
    	{
    		this.dashAnimationState.updateWhen(this.isUsingSkill(1), this.tickCount);
    		this.blockAnimationState.updateWhen(this.isUsingSkill(2), this.tickCount);
    		this.jumpAnimationState.updateWhen(this.isUsingSkill(3), this.tickCount);
    		this.smashAnimationState.updateWhen(this.isUsingSkill(4), this.tickCount);
    		this.backdashAnimationState.updateWhen(this.isUsingSkill(5), this.tickCount);
    	}
    	
    	if(this.getTarget() != null)
    	{
    		if(this.canLook())
    		{
    			this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
    		}
    		if(this.canMove())
    		{
    			this.getNavigation().moveTo(this.getTarget(), 0.8F);
    		}
    	}
    	
    	if(this.getAnimationState() == 4 || this.getAnimationState() == 2)
    	{
    		this.setDeltaMovement(this.getDeltaMovement().multiply(0, 1, 0));
    		this.getNavigation().stop();
    	}
    	
    	if(this.getAnimationState() == 4 && this.getAnimationTick() <= 0 && this.random.nextBoolean())
    	{
    		this.goal = DeadmanBackDashGoal.class;
    	}
    	
    	if(this.getAnimationState() == 3 && this.getAnimationTick() < 6)
    	{
    		Vec3 groundPos = MultiverseUtil.getGroundPos(this.level, this.getX(), this.getY(), this.getZ(), -1);
    		this.setPos(groundPos);
    		
    		for(int i = 0; i < 150; i++)
    		{
        		this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.level.getBlockState(BlockPos.containing(groundPos).below())), this.getX() + this.random.nextGaussian() * 2.0F, this.getY(), this.getZ() + this.random.nextGaussian() * 2.0F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 3.0F, this.random.nextGaussian() * 0.1F);
    		}
    	}
    }
    
    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) 
    {
    	if(!p_21016_.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && p_21016_.getDirectEntity() != null)
    	{
        	if(!this.isUsingSkill())
        	{
        		if(this.getAnimationState() != 2 && Math.random() <= 0.1F)
        		{
            		this.setAnimationState(2);
            		this.setAnimationTick(40);
            		this.setUsingSkill(true);
            		return false;
        		}
        	}
        	else
    		{
        		return false;
    		}
    	}
    	return super.hurt(p_21016_, p_21017_);
    }
    
    @Override
    public void handleEntityEvent(byte p_21375_)
    {
    	super.handleEntityEvent(p_21375_);
    	
    	if(p_21375_ == 99)
    	{
    		for(int i = 0; i < 30; i++)
    		{
        		this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockStateOn()), this.getX() + this.random.nextGaussian() * 0.1F, this.getY(), this.getZ() + this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.01F, this.random.nextGaussian() * 0.1F);
    		}
    	}
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
    {
        this.setItemSlot(EquipmentSlot.MAINHAND, Items.DIAMOND_SWORD.getDefaultInstance());
        this.setItemSlot(EquipmentSlot.OFFHAND, Items.DIAMOND_SWORD.getDefaultInstance());
    	return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }
}

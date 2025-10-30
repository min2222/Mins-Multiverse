package com.min01.multiverse.entity.ai.goal;

import java.util.List;

import com.min01.multiverse.entity.EntityCameraShake;
import com.min01.multiverse.entity.living.EntityDeadman;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class DeadmanJumpGoal extends BasicAnimationSkillGoal<EntityDeadman>
{
	public DeadmanJumpGoal(EntityDeadman mob)
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setDeltaMovement(0.0F, 3.0F, 0.0F);
	}
	
	@Override
	public boolean canUse()
	{
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) <= 8.0F && this.mob.onGround();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.mob.resetFallDistance();
		if(this.mob.getAnimationTick() < 15 && this.mob.getAnimationState() == 0)
		{
			this.mob.setAnimationState(3);
		}

		if(this.mob.getAnimationTick() == 18 && this.mob.getAnimationState() == 4)
		{
			List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(12.0F), t -> t != this.mob && !t.isAlliedTo(this.mob));
			list.forEach(t ->
			{
				if(this.mob.doHurtTarget(t))
				{
					this.mob.heal(5.0F);
				}
			});
		}
	}

	@Override
	protected void performSkill() 
	{
		Vec3 groundPos = MultiverseUtil.getGroundPos(this.mob.level, this.mob.getX(), this.mob.getY(), this.mob.getZ(), -1);
		this.mob.setPos(groundPos);
		
		EntityCameraShake.cameraShake(this.mob.level, this.mob.position(), 40, 0.35F, 0, 15);
		this.mob.setAnimationState(4);
		this.mob.setUsingSkill(true);
		this.mob.setAnimationTick(20);
		this.mob.setDeltaMovement(Vec3.ZERO);
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 20;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 15;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 80;
	}
}

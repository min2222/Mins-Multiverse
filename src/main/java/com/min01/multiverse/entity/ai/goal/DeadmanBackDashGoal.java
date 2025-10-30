package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityDeadman;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.phys.Vec3;

public class DeadmanBackDashGoal extends BasicAnimationSkillGoal<EntityDeadman>
{
	public DeadmanBackDashGoal(EntityDeadman mob)
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.level.broadcastEntityEvent(this.mob, (byte) 99);
		this.mob.setAnimationState(5);
		MultiverseUtil.dashBackward(this.mob, 4.0F, 0.1F);
	}
	
	@Override
	public boolean canUse()
	{
		return super.canUse() && this.mob.onGround() && (this.mob.distanceTo(this.mob.getTarget()) <= 6.0F || this.mob.goal == this.getClass());
	}

	@Override
	protected void performSkill() 
	{
		
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setDeltaMovement(Vec3.ZERO);
		this.mob.goal = null;
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 15;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 4;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		if(this.mob.getHealth() <= this.mob.getMaxHealth() / 2.0F)
		{
			return 5;
		}
		return 40;
	}
}

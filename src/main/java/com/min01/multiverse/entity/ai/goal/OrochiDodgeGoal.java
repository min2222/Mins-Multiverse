package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.ChainType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.phys.Vec3;

public class OrochiDodgeGoal extends BasicAnimationSkillGoal<EntityOrochi>
{
	public OrochiDodgeGoal(EntityOrochi mob)
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
		if(!this.mob.isSnake())
		{
			Vec3 lookPos = MultiverseUtil.getLookPos(this.mob.getRotationVector(), this.mob.position(), 0, 0, -20);
			this.mob.addChain(lookPos, this.mob.position(), false, 1.0F, ChainType.CARRY);
		}
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F && !this.mob.isSnake();
	}

	@Override
	protected void performSkill() 
	{
		
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 1;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		return 1;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 100;
	}
}

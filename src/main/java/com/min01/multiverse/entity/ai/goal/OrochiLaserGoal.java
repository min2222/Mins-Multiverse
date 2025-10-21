package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.ChainType;

public class OrochiLaserGoal extends BasicAnimationSkillGoal<EntityOrochi>
{
	public OrochiLaserGoal(EntityOrochi mob)
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
		if(this.mob.isSnake())
		{
			this.mob.setWantedPos(this.mob.getTarget().getEyePosition().add(0, 10, 0));
			this.mob.setAnim(true);
		}
		else
		{
			this.mob.addChain(this.mob.position().subtract(0, 10, 0), this.mob.position().add(0, 100, 0), true, 10.0F, ChainType.LASER);
		}
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && (this.mob.isSnake() ? this.mob.getChainType() == ChainType.LASER && this.mob.isReached() : true);
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.isSnake())
		{
			this.mob.setLaser(true);
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		if(this.mob.isSnake())
		{
			this.mob.setAnim(false);
			this.mob.setLaser(false);
			this.mob.setAnchorPos(null);
			this.mob.setWantedPos(this.mob.position().subtract(0, 400, 0));
		}
	}

	@Override
	protected int getSkillUsingTime() 
	{
		if(this.mob.isSnake())
		{
			return 200;
		}
		return 1;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		if(this.mob.isSnake())
		{
			return 50;
		}
		return 1;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 300;
	}
}

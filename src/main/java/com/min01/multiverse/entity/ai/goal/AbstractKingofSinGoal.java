package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityKingofSin;

public abstract class AbstractKingofSinGoal extends BasicAnimationSkillGoal<EntityKingofSin>
{
	public AbstractKingofSinGoal(EntityKingofSin mob)
	{
		super(mob);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() || this.getClass() == this.mob.goal;
	}
	
	@Override
	public void stop()
	{
		super.stop();
		if(this.getClass() == this.mob.goal)
		{
			this.mob.goal = null;
		}
	}
}
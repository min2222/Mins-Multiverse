package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.ChainType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

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
			this.mob.setAnim(true);
			this.mob.setWantedPos(this.mob.getTarget().getEyePosition());
		}
		else
		{
			for(int i = 0; i < 360; i += 45)
			{
				Vec2 rot = new Vec2(0, i);
				Vec3 lookPos = MultiverseUtil.getLookPos(rot, this.mob.position(), 0, 0, 8);
				this.mob.addChain(lookPos, lookPos.add(0, MultiverseUtil.distanceToY(this.mob, this.mob.getTarget()) + 10, 0), true, 1.0F, ChainType.LASER);
			}
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
			this.mob.setWantedPos(this.mob.position().subtract(0, 200, 0));
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
			return 100;
		}
		return 1;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 300;
	}
}

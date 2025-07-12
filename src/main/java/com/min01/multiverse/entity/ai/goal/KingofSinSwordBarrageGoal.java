package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityKingofSin;
import com.min01.multiverse.entity.projectile.EntitySin;
import com.min01.multiverse.entity.projectile.EntitySin.SinType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.phys.Vec3;

public class KingofSinSwordBarrageGoal extends AbstractKingofSinGoal
{
	public KingofSinSwordBarrageGoal(EntityKingofSin mob) 
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
	}
	
	@Override
	public boolean additionalStartCondition() 
	{
		return this.mob.distanceTo(this.mob.getTarget()) <= 3.0F;
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.getTarget() != null)
		{
			for(int i = 0; i < 10; i++)
			{
				Vec3 targetPos = this.mob.getTarget().position().add(MultiverseUtil.getMinMaxRandomValue(-1, 1), 0, MultiverseUtil.getMinMaxRandomValue(-1, 1));
				Vec3 pos = this.mob.position().add(MultiverseUtil.getMinMaxRandomValue(-4, 4), 30, MultiverseUtil.getMinMaxRandomValue(-4, 4));
				Vec3 motion = MultiverseUtil.fromToVector(pos, targetPos, 3.0F);
				EntitySin sin = new EntitySin(this.mob.level, this.mob);
				sin.setPos(pos);
				sin.setSinType(SinType.GATE_SWORD);
				sin.setDeltaMovement(motion);
				this.mob.level.addFreshEntity(sin);
			}
			this.mob.goal = KingofSinGateGoal.class;
			MultiverseUtil.dashBackward(this.mob, 3.0F, 0.15F);
		}
	}

	@Override
	public void stop()
	{
		super.stop();
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
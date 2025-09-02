package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityKingofSin;
import com.min01.multiverse.entity.projectile.EntitySin;
import com.min01.multiverse.entity.projectile.EntitySin.SinType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class KingofSinGateGoal extends AbstractKingofSinGoal
{
	public KingofSinGateGoal(EntityKingofSin mob) 
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) >= 15.0F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getTarget() != null)
		{
			Vec2 rot = MultiverseUtil.lookAt(this.mob.getEyePosition(), this.mob.getTarget().getEyePosition());
			Vec3 pos = MultiverseUtil.getLookPos(rot, this.mob.position(), MultiverseUtil.getMinMaxRandomValue(-64.0F, 64.0F), MultiverseUtil.getMinMaxRandomValue(5, 64), MultiverseUtil.getMinMaxRandomValue(-2, 2));
			EntitySin sin = new EntitySin(this.mob.level, this.mob);
			sin.setPos(pos);
			sin.setDeltaMovement(Vec3.ZERO);
			rot = MultiverseUtil.lookAt(pos, this.mob.getTarget().getEyePosition());
			sin.setRotation(new Vec3(rot.x, rot.y, 0.0F));
			sin.setSinType(SinType.GATE);
			this.mob.level.addFreshEntity(sin);
		}
	}

	@Override
	protected void performSkill() 
	{
		
	}

	@Override
	public void stop()
	{
		super.stop();
	}

	@Override
	protected int getSkillUsingTime()
	{
		return 100;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		return 1;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 20;
	}
}

package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityRakta;
import com.min01.multiverse.entity.projectile.EntityScarletMagic;
import com.min01.multiverse.entity.projectile.EntityScarletMagic.ScarletMagicType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class RaktaScarletMagicGoal extends AbstractRaktaGoal
{
	public int count;
	public int dist;
	
	public RaktaScarletMagicGoal(EntityRakta mob) 
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
		this.dist = (int) Math.floor(this.mob.distanceTo(this.mob.getTarget()));
	}
	
	@Override
	public boolean additionalStartCondition() 
	{
		return this.mob.distanceTo(this.mob.getTarget()) >= 8.0F;
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.getTarget() != null)
		{
			Vec2 rot = MultiverseUtil.lookAt(this.mob.getEyePosition(), this.mob.getTarget().getEyePosition());
			Vec3 lookPos = MultiverseUtil.getLookPos(new Vec2(0, rot.y), this.mob.getEyePosition().add(0, 20, 0), 0, 0, this.count * 8.0F);
			EntityScarletMagic magic = new EntityScarletMagic(this.mob.level, this.mob);
			magic.setPos(lookPos);
			magic.setDeltaMovement(Vec3.ZERO);
			magic.setRotation(new Vec3(-90.0F, rot.y, 0.0F));
			magic.setScarletMagicType(ScarletMagicType.GATE);
			this.mob.level.addFreshEntity(magic);
			if(this.count < this.dist / 2.0F)
			{
				this.skillWarmupDelay = this.adjustedTickDelay(10);
				this.count++;
			}
		}
	}

	@Override
	public void stop()
	{
		super.stop();
		this.count = 0;
		this.dist = 0;
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
		return 200;
	}
}

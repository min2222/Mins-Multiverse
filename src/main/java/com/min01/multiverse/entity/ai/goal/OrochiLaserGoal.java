package com.min01.multiverse.entity.ai.goal;

import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.OrochiChain.ChainType;
import com.min01.multiverse.network.MultiverseNetwork;
import com.min01.multiverse.network.OrochiChainSyncPacket;
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
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() == 1;
	}

	@Override
	protected void performSkill() 
	{
		for(int i = 0; i < 360; i += 45)
		{
			Vec2 rot = new Vec2(0, i);
			Vec3 lookPos = MultiverseUtil.getLookPos(rot, this.mob.position(), 0, 0, 8);
			this.mob.addChain(lookPos, lookPos.add(0, 10, 0), true, 30, 1.0F, ChainType.LASER);
		}
		//TODO split to actual entity
		MultiverseNetwork.sendToAll(new OrochiChainSyncPacket(this.mob.getUUID()));
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.getChainByType(ChainType.LASER, t -> 
		{
			t.openMouth = false;
			t.isLaser = false;
			t.lastTick = 0;
			t.setAnchorPos(null);
			t.setTarget(t.getTipSegment().getPos().subtract(0, 200, 0));
		});
		//TODO split to actual entity
		MultiverseNetwork.sendToAll(new OrochiChainSyncPacket(this.mob.getUUID()));
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 200;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		return 1;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 300;
	}
}

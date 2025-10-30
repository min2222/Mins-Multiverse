package com.min01.multiverse.entity.ai.goal;

import java.util.ArrayList;
import java.util.List;

import com.min01.multiverse.entity.living.EntityDeadman;
import com.min01.multiverse.network.AddVFXPacket;
import com.min01.multiverse.network.MultiverseNetwork;
import com.min01.multiverse.util.MultiverseUtil;
import com.min01.multiverse.vfx.Effect;
import com.min01.multiverse.vfx.EffectSlash;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class DeadmanDashGoal extends BasicAnimationSkillGoal<EntityDeadman>
{
	public DeadmanDashGoal(EntityDeadman mob)
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.level.broadcastEntityEvent(this.mob, (byte) 99);
		this.mob.setAnimationState(1);
		this.mob.lookAt(Anchor.EYES, this.mob.getTarget().getEyePosition());
		if(this.mob.getHealth() <= this.mob.getMaxHealth() / 2.0F)
		{
			MultiverseUtil.dashToward(this.mob, 8.0F);
		}
		else
		{
			MultiverseUtil.dashToward(this.mob, 12.0F);
		}
	}
	
	@Override
	public boolean canUse()
	{
		return super.canUse() && this.mob.onGround() && this.mob.distanceTo(this.mob.getTarget()) >= 12.0F;
	}

	@Override
	protected void performSkill() 
	{
		List<LivingEntity> arrayList = new ArrayList<>();
    	Vec3 startPos = this.mob.position();
		Vec3 lookPos = MultiverseUtil.getLookPos(new Vec2(0.0F, this.mob.getYHeadRot()), startPos, 0.0F, 0.0F, 15.0F);
		HitResult hitResult = this.mob.level.clip(new ClipContext(startPos, lookPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.mob));
    	Vec3 hitPos = hitResult.getLocation();
        Vec3 targetPos = hitPos.subtract(startPos);
        Vec3 normalizedPos = targetPos.normalize();
        for(int i = 1; i < Mth.floor(targetPos.length()); ++i)
        {
        	Vec3 pos = startPos.add(normalizedPos.scale(i));
        	List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(pos, pos).inflate(this.mob.getBbWidth()), t -> t != this.mob && !t.isAlliedTo(this.mob));
        	arrayList.addAll(list);
        }
        arrayList.forEach(t -> 
        {
        	if(this.mob.doHurtTarget(t))
        	{
        		for(int i = 0; i < 5; i++)
        		{
        			Effect slash = new EffectSlash(0).setSlashProperties(30.0F + t.level.random.nextFloat() * 120.0F, 30.0F + t.level.random.nextFloat() * 120.0F, 30.0F + t.level.random.nextFloat() * 120.0F).setSlashSize(5.0F).setColor(0.35F, 0.35F, 1.0F, 1.0F).setPosition((float)t.getX(), (float)t.getY() + t.getBbHeight() / 2.0F, (float)t.getZ()).setAdditive(true).setLife(10);
        			MultiverseNetwork.sendToAll(new AddVFXPacket(slash, this.mob.getUUID()));
        		}
				this.mob.heal(2.0F);
        	}
        });
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setDeltaMovement(Vec3.ZERO);
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
			return 10;
		}
		return 60;
	}
}

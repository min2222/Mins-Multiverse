package com.min01.multiverse.entity.ai.control;

import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MultiverseSwimmingMoveControl extends MoveControl 
{
	private final int maxTurnX;
	private final float inWaterSpeedModifier;
	private final float outsideWaterSpeedModifier;
	private final boolean applyGravity;
	private Vec3 targetPos = Vec3.ZERO;

	public MultiverseSwimmingMoveControl(Mob mob, int maxTurnX, float inWaterSpeedModifier, float outsideWaterSpeedModifier, boolean applyGravity)
	{
		super(mob);
		this.maxTurnX = maxTurnX;
		this.inWaterSpeedModifier = inWaterSpeedModifier;
		this.outsideWaterSpeedModifier = outsideWaterSpeedModifier;
		this.applyGravity = applyGravity;
	}

	@Override
	public void tick() 
	{
		if(this.applyGravity && this.mob.isInWater()) 
		{
			this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
		}
		if(this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) 
		{
			if(this.mob.tickCount % 60 == 0)
			{
				this.generateNewTarget();
			}
			double d0 = this.targetPos.x - this.mob.getX();
			double d1 = this.targetPos.y - this.mob.getY();
			double d2 = this.targetPos.z - this.mob.getZ();
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			if(d3 < (double) 2.5000003E-7F) 
			{
				this.mob.setZza(0.0F);
			}
			else 
			{
				float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 5.0F));
				this.mob.yBodyRot = this.mob.getYRot();
				this.mob.yHeadRot = this.mob.getYRot();
				float f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if(this.mob.isInWater()) 
				{
					this.mob.setSpeed(f1 * this.inWaterSpeedModifier);
					double d4 = Math.sqrt(d0 * d0 + d2 * d2);
					if(Math.abs(d1) > (double) 1.0E-5F || Math.abs(d4) > (double) 1.0E-5F) 
					{
						float f3 = -((float) (Mth.atan2(d1, d4) * (double) (180.0F / (float) Math.PI)));
						f3 = Mth.clamp(Mth.wrapDegrees(f3), (float) (-this.maxTurnX), (float) this.maxTurnX);
						this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f3, 5.0F));
					}
					float f6 = Mth.cos(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					float f4 = Mth.sin(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					this.mob.zza = f6 * f1;
					this.mob.yya = -f4 * f1;
				}
				else
				{
					float f5 = Math.abs(Mth.wrapDegrees(this.mob.getYRot() - f));
					float f2 = getTurningSpeedFactor(f5);
					this.mob.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
				}
			}
		} 
		else 
		{
			this.mob.setSpeed(0.0F);
			this.mob.setXxa(0.0F);
			this.mob.setYya(0.0F);
			this.mob.setZza(0.0F);
		}
	}
	
    private void generateNewTarget() 
    {
        Level world = this.mob.level;
        int radius = 25;
        for(int i = 0; i < 10; i++)
        {
        	Vec3 pos = MultiverseUtil.getSpreadPosition(this.mob, radius);
        	HitResult hitResult = world.clip(new ClipContext(this.mob.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.mob));
        	if(hitResult instanceof BlockHitResult blockHit)
        	{
                BlockPos targetPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(targetPos);
                if(blockState.is(Blocks.WATER))
                {
                	this.targetPos = blockHit.getLocation();
                	break;
                }
        	}
        }
    }

	private static float getTurningSpeedFactor(float p_249853_) 
	{
		return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
	}
	
    public void setTargetPos(Vec3 pos)
    {
    	this.targetPos = pos;
    }
    
    public Vec3 getTargetPos()
    {
    	return this.targetPos;
    }
}
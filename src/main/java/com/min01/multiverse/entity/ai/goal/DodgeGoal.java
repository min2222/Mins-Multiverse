package com.min01.multiverse.entity.ai.goal;

import java.util.ArrayList;
import java.util.EnumSet;

import com.min01.multiverse.entity.AbstractAnimatableMonster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.phys.Vec3;

public class DodgeGoal extends Goal 
{
    public static void doDodgeCheckForArrow(Entity arrow, PathfinderMob mob)
    {
        if(!(arrow.level instanceof ServerLevel)) 
        	return;
        float width = arrow.getBbWidth() + 0.3F;
        Vec3 arrowMotion = arrow.getDeltaMovement();
        double vH = Math.sqrt(arrowMotion.x * arrowMotion.x + arrowMotion.z * arrowMotion.z);
        Vec3 arrowDirection = new Vec3(arrowMotion.x / vH, arrowMotion.y / vH, arrowMotion.z / vH);
        
        int rangeVertical = 16;
        int rangeHorizontal = 24;
        int distanceY = Math.abs((int) mob.position().y - (int) arrow.position().y);
        if(distanceY <= rangeVertical) 
        {
            double distanceX = mob.position().x - arrow.position().x;
            double distanceZ = mob.position().z - arrow.position().z;
            double distanceH = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
            if(distanceH <= rangeHorizontal)
            {
                double cos = (arrowDirection.x * distanceX + arrowDirection.z * distanceZ) / distanceH;
                double sin = Math.sqrt(1 - cos * cos);
                if(width >= distanceH * sin)
                {
                    tryDodgeArrow(mob, arrowDirection);
                }
            }
        }
    }
    
    private static void tryDodgeArrow(PathfinderMob entity, Vec3 arrowDirection) 
    {
        for(WrappedGoal task : new ArrayList<>(entity.goalSelector.getAvailableGoals())) 
        {
            if(task.getGoal() instanceof DodgeGoal) 
            {
                ((DodgeGoal) task.getGoal()).setDodgeTarget(arrowDirection);
            }
        }
    }
    
    protected AbstractAnimatableMonster mob;
    private double dodgeChance;
    
    private Vec3 arrowMotionDirection;
    private int dodgeDelay;
    private float velocity;
    
    public DodgeGoal(AbstractAnimatableMonster entity, double chance, float velocity) 
    {
        this.mob = entity;
        this.dodgeChance = chance;
        this.velocity = velocity;
        this.setFlags(EnumSet.of(Flag.JUMP));
    }
    
    private void setDodgeTarget(Vec3 arrowDirection) 
    {
        if(arrowDirection == null)
        {
        	this.arrowMotionDirection = null;
        }
        else if(this.dodgeDelay <= 0 && this.mob.getRandom().nextDouble() < this.dodgeChance)
        {
        	this.arrowMotionDirection = arrowDirection;
        }
    }
    
    @Override
    public boolean canUse()
    {
        return this.dodgeDelay-- <= 0 && this.arrowMotionDirection != null && !this.mob.isPassenger();
    }
    
    @Override
    public void start() 
    {
        if(this.arrowMotionDirection != null) 
        {
            Vec3 selfAxis = new Vec3(0.0, 1.0, 0.0);
            Vec3 dodgeDirection = selfAxis.cross(this.arrowMotionDirection);
            double velocity = this.velocity;
            if(this.mob.getRandom().nextBoolean()) 
            {
            	velocity = -velocity;
            }
            if(this.mob.onGround())
            {
                this.mob.setDeltaMovement(dodgeDirection.x * velocity, 0, dodgeDirection.z * velocity);
            }
            this.setDodgeTarget(null);
            this.dodgeDelay = 0;
        }
    }
    
    @Override
    public boolean canContinueToUse() 
    {
        return false;
    }
}

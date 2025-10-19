package com.min01.multiverse.util;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;
import org.joml.Math;

import com.min01.multiverse.entity.AbstractAnimatableMonster;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class MultiverseUtil 
{
	public static final Method GET_ENTITY = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
	
	@SuppressWarnings("deprecation")
	public static Vec3 getGroundPos(BlockGetter pLevel, double pX, double startY, double pZ, int belowY)
    {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(pX, startY, pZ);
        do
        {
        	mutablePos.move(Direction.DOWN);
        } 
        while((pLevel.getBlockState(mutablePos).isAir() || pLevel.getBlockState(mutablePos).liquid() || !pLevel.getBlockState(mutablePos).isCollisionShapeFullBlock(pLevel, mutablePos)) && mutablePos.getY() > pLevel.getMinBuildHeight());

        BlockPos blockPos = mutablePos.below(belowY);

        return Vec3.atBottomCenterOf(blockPos);
    }
	
    public static float distanceToY(Entity entity, Entity target)
    {
        float f = (float)(entity.getY() - target.getY());
        return Mth.sqrt(f * f);
    }
	
    public static float distanceToXZ(Entity entity, Entity target)
    {
        float f = (float)(entity.getX() - target.getX());
        float f2 = (float)(entity.getZ() - target.getZ());
        return Mth.sqrt(f * f + f2 * f2);
    }
	
	public static Pair<Boolean, Vec3> getDodge(Entity arrow, Mob mob)
	{
        float width = arrow.getBbWidth() + 1.5F;
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
            	return Pair.of(width >= distanceH * sin, arrowDirection);
            }
        }
        return Pair.of(false, Vec3.ZERO);
	}
	
	public static Vec3 randomPointAroundBox(AABB box, RandomSource rand, float radius) 
	{
		double x = Mth.lerp(rand.nextDouble(), box.minX, box.maxX) + (rand.nextDouble() - 0.5) * radius;
		double y = Mth.lerp(rand.nextDouble(), box.minY, box.maxY) + (rand.nextDouble() - 0.5) * radius;
		double z = Mth.lerp(rand.nextDouble(), box.minZ, box.maxZ) + (rand.nextDouble() - 0.5) * radius;
		return new Vec3(x, y, z);
	}
	
	public static void dashSide(AbstractAnimatableMonster entity, float scale, int direction)
	{
		boolean isRight = direction == 1;
		boolean isLeft = direction == 2;
		int rot = isRight ? 180 : isLeft ? 0 : 90;
        float f1 = (float) Math.cos(Math.toRadians(entity.getYRot() + rot));
        float f2 = (float) Math.sin(Math.toRadians(entity.getYRot() + rot));
        entity.push(f1 * scale, 0, f2 * scale);
	}
	
	public static void dashBackward(AbstractAnimatableMonster entity, float scale, float yScale)
	{
        float f1 = (float) Math.cos(Math.toRadians(entity.getYRot() - 90));
        float f2 = (float) Math.sin(Math.toRadians(entity.getYRot() - 90));
        entity.push(f1 * scale, yScale, f2 * scale);
	}
	
	public static void dashToward(AbstractAnimatableMonster entity, float scale)
	{
        float f1 = (float) Math.cos(Math.toRadians(entity.getYRot() + 90));
        float f2 = (float) Math.sin(Math.toRadians(entity.getYRot() + 90));
        entity.push(f1 * scale, 0, f2 * scale);
	}
	
	public static float getMinMaxRandomValue(float min, float max)
	{
		return min + (float)(Math.random() * (max - min));
	}
	
	public static Vec3 fromToVector(Vec3 from, Vec3 to, float scale)
	{
		Vec3 motion = to.subtract(from).normalize();
		return motion.scale(scale);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T getEntityByUUID(Level level, UUID uuid)
	{
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) GET_ENTITY.invoke(level);
			return (T) entities.get(uuid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Level getClientLevel()
	{
		Optional<Level> optional = LogicalSidedProvider.CLIENTWORLD.get(LogicalSide.CLIENT).filter(ClientLevel.class::isInstance);
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;
	}
	
	public static void getClientLevel(Consumer<Level> consumer)
	{
		LogicalSidedProvider.CLIENTWORLD.get(LogicalSide.CLIENT).filter(ClientLevel.class::isInstance).ifPresent(level -> 
		{
			consumer.accept(level);
		});
	}
	
	public static Vec3 getRandomPosition(Entity entity, int range)
	{
    	Vec3 vec3 = entity.position().add(Mth.randomBetweenInclusive(entity.level.random, -range, range), Mth.randomBetweenInclusive(entity.level.random, -range, range), Mth.randomBetweenInclusive(entity.level.random, -range, range));
        return vec3;
	}
	
	public static Vec3 getSpreadPosition(Entity entity, double range)
	{
        double x = entity.getX() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range + 0.5D;
        double y = entity.getY() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range + 0.5D;
        double z = entity.getZ() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range + 0.5D;
        return new Vec3(x, y, z);
	}
	
	public static Vec3 getSpreadPosition(Level level, Vec3 startPos, Vec3 range)
	{
        double x = startPos.x + (level.random.nextDouble() - level.random.nextDouble()) * range.x + 0.5D;
        double y = startPos.y + (level.random.nextDouble() - level.random.nextDouble()) * range.y + 0.5D;
        double z = startPos.z + (level.random.nextDouble() - level.random.nextDouble()) * range.z + 0.5D;
        return new Vec3(x, y, z);
	}
	
	public static Vec3 getSpreadPosition(Level level, Vec3 startPos, double range)
	{
        double x = startPos.x + (level.random.nextDouble() - level.random.nextDouble()) * range + 0.5D;
        double y = startPos.y + (level.random.nextDouble() - level.random.nextDouble()) * range + 0.5D;
        double z = startPos.z + (level.random.nextDouble() - level.random.nextDouble()) * range + 0.5D;
        return new Vec3(x, y, z);
	}
	
	public static Vec3 getLookPos(Vec2 rotation, Vec3 position, double left, double up, double forwards) 
	{
		Vec2 vec2 = rotation;
		Vec3 vec3 = position;
		float f = Mth.cos((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f1 = Mth.sin((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f2 = Mth.cos(-vec2.x * ((float)Math.PI / 180.0F));
		float f3 = Mth.sin(-vec2.x * ((float)Math.PI / 180.0F));
		float f4 = Mth.cos((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		float f5 = Mth.sin((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		Vec3 vec31 = new Vec3((double)(f * f2), (double)f3, (double)(f1 * f2));
		Vec3 vec32 = new Vec3((double)(f * f4), (double)f5, (double)(f1 * f4));
		Vec3 vec33 = vec31.cross(vec32).scale(-1.0D);
		double d0 = vec31.x * forwards + vec32.x * up + vec33.x * left;
		double d1 = vec31.y * forwards + vec32.y * up + vec33.y * left;
		double d2 = vec31.z * forwards + vec32.z * up + vec33.z * left;
		return new Vec3(vec3.x + d0, vec3.y + d1, vec3.z + d2);
	}
	
	public static Vec2 lookAt(Vec3 startPos, Vec3 pos)
	{
		Vec3 vec3 = startPos;
		double d0 = pos.x - vec3.x;
		double d1 = pos.y - vec3.y;
		double d2 = pos.z - vec3.z;
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		float xRot = Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * (double)(180.0F / (float)Math.PI))));
		float yRot = Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * (double)(180.0F / (float)Math.PI)) - 90.0F);
	    return new Vec2(xRot, yRot);
	}
	
	public static float rotlerp(float p_24992_, float p_24993_, float p_24994_)
	{
		float f = Mth.wrapDegrees(p_24993_ - p_24992_);
		
		if(f > p_24994_) 
		{
			f = p_24994_;
		}

		if(f < -p_24994_) 
		{
			f = -p_24994_;
		}

		float f1 = p_24992_ + f;
		
		if(f1 < 0.0F)
		{
			f1 += 360.0F;
		}
		else if(f1 > 360.0F)
		{
			f1 -= 360.0F;
		}
		
		return f1;
	}
}

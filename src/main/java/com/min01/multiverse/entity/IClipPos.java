package com.min01.multiverse.entity;

import net.minecraft.world.phys.Vec3;

public interface IClipPos 
{
	default boolean shouldClip()
	{
		return true;
	}
	
	public Vec3 getClipPos(Vec3 from, Vec3 to);
}
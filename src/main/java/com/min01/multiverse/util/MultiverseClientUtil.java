package com.min01.multiverse.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;

public class MultiverseClientUtil 
{
	public static final Minecraft MC = Minecraft.getInstance();
	
	public static void animateHead(ModelPart head, float netHeadYaw, float headPitch)
	{
		head.yRot += Math.toRadians(netHeadYaw);
		head.xRot += Math.toRadians(headPitch);
	}
}

package com.min01.multiverse.obj;

import org.joml.Vector3f;

import net.minecraft.client.animation.Keyframe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public record ObjAnimationChannel(ObjAnimationChannel.Target target, Keyframe... keyframes)
{
	@OnlyIn(Dist.CLIENT)
	public interface Target 
	{
		void apply(WavefrontObject p_232248_, Vector3f p_253771_);
	}

	@OnlyIn(Dist.CLIENT)
	public static class Targets 
	{
		public static final ObjAnimationChannel.Target POSITION = WavefrontObject::offsetPos;
		public static final ObjAnimationChannel.Target ROTATION = WavefrontObject::offsetRotation;
		public static final ObjAnimationChannel.Target SCALE = WavefrontObject::offsetScale;
		public static final ObjAnimationChannel.Target UV = WavefrontObject::offsetUV;
	}
}

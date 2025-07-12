package com.min01.multiverse.obj;

import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.Maps;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public record ObjAnimationDefinition(float lengthInSeconds, boolean looping, Map<String, List<ObjAnimationChannel>> boneAnimations)
{
	@OnlyIn(Dist.CLIENT)
	public static class Builder 
	{
		private final float length;
		private final Map<String, List<ObjAnimationChannel>> animationByBone = Maps.newHashMap();
		private boolean looping;

		public static ObjAnimationDefinition.Builder withLength(float p_232276_)
		{
			return new ObjAnimationDefinition.Builder(p_232276_);
		}

		private Builder(float p_232273_) 
		{
			this.length = p_232273_;
		}

		public ObjAnimationDefinition.Builder looping()
		{
			this.looping = true;
			return this;
		}

		public ObjAnimationDefinition.Builder addAnimation(String p_232280_, ObjAnimationChannel p_232281_) 
		{
			this.animationByBone.computeIfAbsent(p_232280_, (p_232278_) -> 
			{
				return Lists.newArrayList();
			}).add(p_232281_);
			return this;
		}

		public ObjAnimationDefinition build() 
		{
			return new ObjAnimationDefinition(this.length, this.looping, this.animationByBone);
		}
	}
}

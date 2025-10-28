package com.min01.multiverse.entity.renderer.layer;

import com.min01.multiverse.entity.living.EntityDeadman;
import com.min01.multiverse.entity.model.ModelDeadman;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class DeadmanItemInHandLayer extends RenderLayer<EntityDeadman, ModelDeadman>
{
	public final ItemInHandRenderer itemRenderer = MultiverseClientUtil.MC.getEntityRenderDispatcher().getItemInHandRenderer();
	
	public DeadmanItemInHandLayer(RenderLayerParent<EntityDeadman, ModelDeadman> p_117346_) 
	{
		super(p_117346_);
	}
	
	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, EntityDeadman p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_)
	{
		ItemStack mainHandStack = p_117352_.getMainHandItem();
		ItemStack offHandStack = p_117352_.getOffhandItem();
		if(!mainHandStack.isEmpty())
		{
			p_117349_.pushPose();
			p_117349_.translate(0, 1.5F, 0);
			this.getParentModel().deadman.translateAndRotate(p_117349_);
			this.getParentModel().body.translateAndRotate(p_117349_);
			this.getParentModel().right_arm.translateAndRotate(p_117349_);
			p_117349_.mulPose(Axis.XP.rotationDegrees(-90.0F));
			p_117349_.translate(-0.05F, 0, 0.5F);
			this.itemRenderer.renderItem(p_117352_, mainHandStack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, p_117349_, p_117350_, p_117351_);
			p_117349_.popPose();
		}
		if(!offHandStack.isEmpty())
		{
			p_117349_.pushPose();
			p_117349_.translate(0, 1.5F, 0);
			this.getParentModel().deadman.translateAndRotate(p_117349_);
			this.getParentModel().body.translateAndRotate(p_117349_);
			this.getParentModel().left_arm.translateAndRotate(p_117349_);
			p_117349_.mulPose(Axis.XP.rotationDegrees(-90.0F));
			p_117349_.translate(-0.05F, 0, 0.5F);
			this.itemRenderer.renderItem(p_117352_, offHandStack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, p_117349_, p_117350_, p_117351_);
			p_117349_.popPose();
		}
	}
}

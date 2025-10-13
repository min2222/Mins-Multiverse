package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.animation.OrochiAnimation;
import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.misc.SmoothAnimationState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelOrochiHead extends HierarchicalModel<EntityOrochi> 
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "orochi_head"), "main");
	private final ModelPart root;

	public ModelOrochiHead(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 8.0F));

		PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(0, 14).addBox(2.0F, -8.0F, -14.0F, 5.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).mirror().addBox(-7.0F, -8.0F, -14.0F, 5.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(36, 32).addBox(-5.0F, -6.0F, -13.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(64, 24).addBox(-4.5F, -1.0F, -13.0F, 9.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(40, 65).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(38, 14).addBox(-4.0F, -4.0F, -20.0F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-3.5F, -0.5F, -19.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		upper_jaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -5.9991F, -4.9996F, 2.7489F, 0.0F, -3.1416F));

		upper_jaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -5.9991F, -4.9996F, 2.7489F, 0.0F, -3.1416F));

		upper_jaw.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.9991F, -2.9996F, 2.5744F, 0.0F, -3.1416F));

		upper_jaw.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -7.9991F, -2.9996F, 2.7489F, 0.0F, -3.1416F));

		upper_jaw.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9991F, -6.5F, -2.9996F, 2.7489F, 0.0F, 1.5708F));

		upper_jaw.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9991F, -5.5F, -0.9996F, 2.7489F, 0.0F, 1.5708F));

		upper_jaw.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -7.9991F, -0.9996F, 2.7489F, 0.0F, 3.1416F));

		upper_jaw.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -7.9991F, -2.9996F, 2.7489F, 0.0F, 3.1416F));

		upper_jaw.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.9991F, -6.5F, -2.9996F, 2.7489F, 0.0F, -1.5708F));

		upper_jaw.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.9991F, -5.5F, -0.9996F, 2.7489F, 0.0F, -1.5708F));

		upper_jaw.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, -7.9991F, -0.9996F, 2.7489F, 0.0F, -3.1416F));

		upper_jaw.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.9991F, -6.9996F, 2.7489F, 0.0F, -3.1416F));

		upper_jaw.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9991F, -0.5F, -3.9996F, 2.5744F, 0.0F, 1.5708F));

		upper_jaw.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9991F, 1.5F, -1.9996F, 2.4435F, 0.0F, 1.5708F));

		upper_jaw.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9991F, -0.5F, -3.9996F, 2.5744F, 0.0F, -1.5708F));

		upper_jaw.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9991F, 1.5F, -1.9996F, 2.4435F, 0.0F, -1.5708F));

		PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -2.0F, -13.0F, 12.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(42, 0).addBox(-3.0F, 1.0F, -19.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(36, 44).addBox(-2.5F, -0.5F, -18.5F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		lower_jaw.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9991F, -0.5F, -5.9996F, 2.7489F, 0.0F, 1.5708F));

		lower_jaw.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(-3, 74).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9991F, 1.5F, -3.9996F, 2.7489F, 0.0F, 1.5708F));

		lower_jaw.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.9991F, -0.5F, -5.9996F, 2.7489F, 0.0F, -1.5708F));

		lower_jaw.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(-3, 74).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.9991F, 1.5F, -3.9996F, 2.7489F, 0.0F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnimOrochi(SmoothAnimationState animationState, EntityOrochi entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		animationState.animate(this, OrochiAnimation.OROCHI_JAW_OPEN, ageInTicks);
	}
	
	@Override
	public void setupAnim(EntityOrochi entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
	}
	
	@Override
	public ModelPart root() 
	{
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
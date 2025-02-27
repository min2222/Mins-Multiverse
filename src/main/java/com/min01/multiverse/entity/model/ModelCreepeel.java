package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
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

public class ModelCreepeel extends HierarchicalModel<EntityCreepeel>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "creepeel"), "main");
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart body_small;
	private final ModelPart back_body_small;
	private final ModelPart front_body_small;
	private final ModelPart body_medium;
	private final ModelPart back_body_medium;
	private final ModelPart front_body_medium;
	private final ModelPart body_large;
	private final ModelPart back_body_large;
	private final ModelPart front_body_large;
	private final ModelPart body_huge;
	private final ModelPart back_body_huge;
	private final ModelPart front_body_huge;
	private final ModelPart fin_small;
	private final ModelPart back_bottom_fin_small;
	private final ModelPart front_bottom_fin_small;
	private final ModelPart back_top_fin_small;
	private final ModelPart front_top_fin_small;
	private final ModelPart fin_medium;
	private final ModelPart back_bottom_fin_medium;
	private final ModelPart front_bottom_fin_medium;
	private final ModelPart medium_bottom_fin_medium;
	private final ModelPart back_top_fin_medium;
	private final ModelPart front_top_fin_medium;
	private final ModelPart fin_large;
	private final ModelPart back_bottom_fin_large;
	private final ModelPart front_bottom_fin_large;
	private final ModelPart back_top_fin_large;
	private final ModelPart front_top_fin_large;
	private final ModelPart medium_top1_fin_large;
	private final ModelPart medium_top2_fin_large;
	private final ModelPart fin_main;
	private final ModelPart right_fin_main;
	private final ModelPart left_fin_main;
	private final ModelPart fin_back;

	public ModelCreepeel(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.body = this.root.getChild("body");
		this.body_small = this.body.getChild("body_small");
		this.back_body_small = this.body_small.getChild("back_body_small");
		this.front_body_small = this.body_small.getChild("front_body_small");
		this.body_medium = this.body.getChild("body_medium");
		this.back_body_medium = this.body_medium.getChild("back_body_medium");
		this.front_body_medium = this.body_medium.getChild("front_body_medium");
		this.body_large = this.body.getChild("body_large");
		this.back_body_large = this.body_large.getChild("back_body_large");
		this.front_body_large = this.body_large.getChild("front_body_large");
		this.body_huge = this.body.getChild("body_huge");
		this.back_body_huge = this.body_huge.getChild("back_body_huge");
		this.front_body_huge = this.body_huge.getChild("front_body_huge");
		this.fin_small = this.root.getChild("fin_small");
		this.back_bottom_fin_small = this.fin_small.getChild("back_bottom_fin_small");
		this.front_bottom_fin_small = this.fin_small.getChild("front_bottom_fin_small");
		this.back_top_fin_small = this.fin_small.getChild("back_top_fin_small");
		this.front_top_fin_small = this.fin_small.getChild("front_top_fin_small");
		this.fin_medium = this.root.getChild("fin_medium");
		this.back_bottom_fin_medium = this.fin_medium.getChild("back_bottom_fin_medium");
		this.front_bottom_fin_medium = this.fin_medium.getChild("front_bottom_fin_medium");
		this.medium_bottom_fin_medium = this.fin_medium.getChild("medium_bottom_fin_medium");
		this.back_top_fin_medium = this.fin_medium.getChild("back_top_fin_medium");
		this.front_top_fin_medium = this.fin_medium.getChild("front_top_fin_medium");
		this.fin_large = this.root.getChild("fin_large");
		this.back_bottom_fin_large = this.fin_large.getChild("back_bottom_fin_large");
		this.front_bottom_fin_large = this.fin_large.getChild("front_bottom_fin_large");
		this.back_top_fin_large = this.fin_large.getChild("back_top_fin_large");
		this.front_top_fin_large = this.fin_large.getChild("front_top_fin_large");
		this.medium_top1_fin_large = this.fin_large.getChild("medium_top1_fin_large");
		this.medium_top2_fin_large = this.fin_large.getChild("medium_top2_fin_large");
		this.fin_main = this.root.getChild("fin_main");
		this.right_fin_main = this.fin_main.getChild("right_fin_main");
		this.left_fin_main = this.fin_main.getChild("left_fin_main");
		this.fin_back = this.root.getChild("fin_back");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 88).addBox(-8.0F, -8.0F, -15.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 7.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_small = body.addOrReplaceChild("body_small", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		body_small.addOrReplaceChild("back_body_small", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 88.0F));

		body_small.addOrReplaceChild("front_body_small", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 80.0F));

		PartDefinition body_medium = body.addOrReplaceChild("body_medium", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		body_medium.addOrReplaceChild("back_body_medium", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 70.0F));

		body_medium.addOrReplaceChild("front_body_medium", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 60.0F));

		PartDefinition body_large = body.addOrReplaceChild("body_large", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		body_large.addOrReplaceChild("back_body_large", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 48.0F));

		body_large.addOrReplaceChild("front_body_large", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 36.0F));

		PartDefinition body_huge = body.addOrReplaceChild("body_huge", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		body_huge.addOrReplaceChild("back_body_huge", CubeListBuilder.create().texOffs(0, 60).addBox(-7.0F, -7.0F, 1.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 21.0F));

		body_huge.addOrReplaceChild("front_body_huge", CubeListBuilder.create().texOffs(0, 60).addBox(-7.0F, -7.0F, 1.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 7.0F));

		PartDefinition fin_small = root.addOrReplaceChild("fin_small", CubeListBuilder.create(), PartPose.offsetAndRotation(0.001F, -9.2625F, 88.4926F, 0.48F, 0.0F, 0.0F));

		fin_small.addOrReplaceChild("back_bottom_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(0.001F, -7.0463F, -1.2758F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.001F, 11.4867F, -1.5911F));

		fin_small.addOrReplaceChild("front_bottom_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(0.0F, 0.7464F, -9.963F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_small.addOrReplaceChild("back_top_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(-0.001F, -8.7877F, -5.0129F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5258F, 7.4446F, 2.1817F, 0.0F, 0.0F));

		fin_small.addOrReplaceChild("front_top_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(-0.001F, -7.9007F, -4.5511F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.3328F, -0.1132F, 2.1817F, 0.0F, 0.0F));

		PartDefinition fin_medium = root.addOrReplaceChild("fin_medium", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 10.5F, 45.0F, 0.48F, 0.0F, 0.0F));

		fin_medium.addOrReplaceChild("back_bottom_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(0.001F, -3.9309F, 26.4089F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 2.0F));

		fin_medium.addOrReplaceChild("front_bottom_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(0.001F, -8.5484F, 17.5388F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 2.0F));

		fin_medium.addOrReplaceChild("medium_bottom_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(-0.999F, -22.4738F, -13.5431F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 2.0F));

		fin_medium.addOrReplaceChild("back_top_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(-4.001F, -4.812F, -8.1925F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.001F, -13.8571F, 35.7106F, 2.1817F, 0.0F, 0.0F));

		fin_medium.addOrReplaceChild("front_top_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(-0.001F, -7.546F, -5.1062F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.999F, -17.5146F, 30.8502F, 2.1817F, 0.0F, 0.0F));

		PartDefinition fin_large = root.addOrReplaceChild("fin_large", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 9.0F, 44.0F, 0.48F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("back_bottom_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.999F, -7.9101F, 8.6273F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("front_bottom_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.999F, -13.4511F, -2.0169F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("back_top_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.001F, -9.2788F, -6.2274F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.999F, -22.4244F, 22.7857F, 2.1817F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("front_top_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.001F, -11.9399F, -7.6127F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.999F, -30.6264F, 13.5269F, 2.1817F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("medium_top1_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(0.0F, -5.0F, -4.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -29.2309F, -0.0618F, 2.1817F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("medium_top2_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(0.0F, -5.0F, -4.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -34.7718F, -10.706F, 2.1817F, 0.0F, 0.0F));

		PartDefinition fin_main = root.addOrReplaceChild("fin_main", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.0F, -1.0F, 16.0F, 0.7854F, 0.0F, 0.0F));

		fin_main.addOrReplaceChild("right_fin_main", CubeListBuilder.create().texOffs(108, 13).addBox(14.9F, -8.0F, -5.0F, 0.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_main.addOrReplaceChild("left_fin_main", CubeListBuilder.create().texOffs(108, 13).addBox(-0.1F, -8.0F, -5.0F, 0.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		root.addOrReplaceChild("fin_back", CubeListBuilder.create().texOffs(74, -20).addBox(0.0F, -30.0F, 48.0F, 0.0F, 16.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 47.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityCreepeel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
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
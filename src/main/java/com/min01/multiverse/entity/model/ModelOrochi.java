package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.util.MultiverseClientUtil;
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
import net.minecraft.util.Mth;

public class ModelOrochi extends HierarchicalModel<EntityOrochi>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "orochi"), "main");
	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart head2;
	private final ModelPart Larm;
	private final ModelPart Rarm;

	public ModelOrochi(ModelPart root)
	{
		this.root = root.getChild("root");
		this.bone = this.root.getChild("bone");
		this.head2 = this.bone.getChild("head2");
		this.Larm = this.bone.getChild("Larm");
		this.Rarm = this.bone.getChild("Rarm");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition head2 = bone.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 25).addBox(-4.5F, -8.5F, -3.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, -5.0F));

		PartDefinition hair = head2.addOrReplaceChild("hair", CubeListBuilder.create(), PartPose.offset(-0.4955F, -8.0721F, -3.625F));

		hair.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -4.0F, 6.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 4.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition ha = head2.addOrReplaceChild("ha", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 5.5F));

		ha.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(36, 16).addBox(-7.0F, 0.0F, 0.0F, 15.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition Larm = bone.addOrReplaceChild("Larm", CubeListBuilder.create(), PartPose.offset(3.25F, -20.75F, -3.75F));

		Larm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(25, 88).addBox(-0.5474F, -1.4329F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.25F, -0.144F, -0.0633F, 0.1734F));

		Larm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(65, 0).addBox(0.025F, 2.3465F, -3.6272F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4534F, -0.1086F, -0.1741F));

		Larm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 57).addBox(0.0F, -1.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1138F, -0.1086F, -0.1741F));

		PartDefinition Rarm = bone.addOrReplaceChild("Rarm", CubeListBuilder.create(), PartPose.offset(-3.25F, -20.75F, -3.75F));

		Rarm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(65, 0).mirror().addBox(-4.025F, 2.3465F, -3.6272F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4534F, 0.1086F, 0.1741F));

		Rarm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(25, 88).mirror().addBox(-4.4526F, -1.4329F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.25F, -0.144F, 0.0633F, -0.1734F));

		Rarm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-4.0F, -1.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1138F, 0.1086F, 0.1741F));

		PartDefinition chest = bone.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(26, 46).addBox(-4.0F, -12.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(91, 0).addBox(-4.5F, -12.5F, -0.25F, 9.0F, 13.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(25, 79).addBox(-4.5F, -4.65F, -0.5F, 9.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -6.0F));

		chest.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(64, 41).addBox(-1.0F, -2.0F, 0.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.75F, -1.0F, 0.0F, 0.0F, 0.7854F));

		chest.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(12, 75).mirror().addBox(-0.5F, -4.5F, 0.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.425F, -8.3F, -0.025F, 0.0F, 0.0F, -0.48F));

		chest.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(12, 75).addBox(-1.5F, -4.5F, 0.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.425F, -8.3F, -0.075F, 0.0F, 0.0F, 0.48F));

		chest.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(18, 62).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -3.875F, -0.45F, 0.0F, 0.0F, 0.2182F));

		chest.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(18, 62).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.875F, -0.45F, 0.0F, 0.0F, -0.2182F));

		chest.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(18, 57).addBox(-2.0F, -2.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail1 = bone.addOrReplaceChild("tail1", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, -6.0F));

		tail1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(64, 32).addBox(-3.0F, -5.0843F, -0.249F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 4.95F, 0.7F, 0.0873F, 0.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(0.0F, 4.9279F, 0.5111F));

		tail2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(36, 32).addBox(-3.0F, -4.0F, -4.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 5.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(-0.5F, 4.5709F, 6.4823F));

		tail3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 43).addBox(-3.0F, -4.0F, -4.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create(), PartPose.offset(0.5F, -0.1353F, 7.993F));

		tail4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(50, 46).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 0.5F, 2.2689F, 0.0F, 0.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0007F, 6.1475F));

		tail5.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(50, 60).addBox(-3.0F, -4.0F, -4.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.45F, -4.5F, -3.0107F, 0.0F, 0.0F));

		PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0438F, -6.689F));

		tail6.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(72, 61).addBox(-3.0F, -4.0F, -4.0F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.95F, 2.75F, 2.5744F, 0.0F, 0.0F));

		PartDefinition tail7 = tail6.addOrReplaceChild("tail7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.85F, 4.05F, 2.618F, 0.0F, 0.0F));

		tail7.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(57, 63).addBox(1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2684F, 0.3315F, 0.0052F, -0.6977F, -0.028F, -0.1207F));

		tail7.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(57, 63).addBox(1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3816F, 0.3315F, 0.0052F, -0.4737F, -0.0803F, -0.2424F));

		tail7.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(57, 63).mirror().addBox(-1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.4066F, 0.3315F, 0.0052F, -0.9158F, 0.028F, -0.0538F));

		tail7.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(57, 63).mirror().addBox(-1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.1566F, 0.3315F, 0.0052F, 0.0F, 0.0F, 0.5672F));

		tail7.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(57, 63).mirror().addBox(-1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3816F, 0.3315F, 0.0052F, -0.4737F, 0.0803F, 0.2424F));

		tail7.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(57, 63).mirror().addBox(-1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.2684F, 0.3315F, 0.0052F, -0.6977F, 0.028F, 0.1207F));

		tail7.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(57, 63).addBox(1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4066F, 0.3315F, 0.0052F, -0.9158F, -0.028F, 0.0538F));

		tail7.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(57, 63).addBox(1.0F, 0.0F, -5.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1566F, 0.3315F, 0.0052F, 0.0F, 0.0F, -0.5672F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityOrochi entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		MultiverseClientUtil.animateHead(!entity.isRiding() ? this.head2 : this.root(), netHeadYaw, headPitch);
		
		this.Rarm.xRot += Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.Larm.xRot += Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
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
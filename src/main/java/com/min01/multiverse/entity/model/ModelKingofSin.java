package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.animation.KingofSinAnimation;
import com.min01.multiverse.entity.living.EntityKingofSin;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.min01.multiverse.util.MultiverseUtil;
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

public class ModelKingofSin extends HierarchicalModel<EntityKingofSin>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "king_of_sin"), "main");
	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart upperbody;
	private final ModelPart neck;
	private final ModelPart head;

	public ModelKingofSin(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.upperbody = this.main.getChild("upperbody");
		this.neck = this.upperbody.getChild("neck");
		this.head = this.neck.getChild("head");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(2.0F, -28.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(-2.0F, 52.0F, 0.0F));

		PartDefinition upperbody = main.addOrReplaceChild("upperbody", CubeListBuilder.create(), PartPose.offset(0.0F, -32.0F, -2.0F));

		PartDefinition RWAAHAHAHA = upperbody.addOrReplaceChild("RWAAHAHAHA", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -5.2442F, -5.8624F, 14.0F, 6.0F, 9.0F, new CubeDeformation(-0.001F))
		.texOffs(78, 29).mirror().addBox(1.0F, 0.7558F, -4.8624F, 5.0F, 8.0F, 8.0F, new CubeDeformation(-0.05F)).mirror(false)
		.texOffs(78, 29).addBox(-6.0F, 0.7558F, -4.8624F, 5.0F, 8.0F, 8.0F, new CubeDeformation(-0.05F))
		.texOffs(0, 36).addBox(-5.0F, -5.2442F, -3.8624F, 10.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.7558F, -0.1376F));

		RWAAHAHAHA.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 15).addBox(-10.0F, -9.0F, -6.0F, 10.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.2442F, 4.1376F, -0.1745F, 0.0F, 0.0F));

		RWAAHAHAHA.addOrReplaceChild("sword", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, -0.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 3.75F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -2.25F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -20.25F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5043F, 13.6527F, 18.6177F, 1.1377F, -0.0552F, -0.1188F));

		RWAAHAHAHA.addOrReplaceChild("sword2", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, 1.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -0.75F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -18.75F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.8213F, -0.5679F, 14.5689F, 1.6863F, 0.3575F, 0.2655F));

		RWAAHAHAHA.addOrReplaceChild("sword3", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, 1.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -0.75F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -18.75F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.2863F, -6.3983F, 15.1159F, 1.8251F, 0.214F, -0.3331F));

		RWAAHAHAHA.addOrReplaceChild("sword4", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, 1.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -0.75F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -18.75F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.2747F, 0.4481F, 16.9315F, 1.6346F, -0.2915F, -0.752F));

		RWAAHAHAHA.addOrReplaceChild("sword5", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, 1.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -0.75F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -18.75F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.343F, 8.155F, 15.9722F, 1.3516F, 0.013F, -0.1765F));

		RWAAHAHAHA.addOrReplaceChild("sword6", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, 1.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -0.75F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -18.75F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.7222F, 6.2047F, 13.9169F, 1.3354F, -0.3698F, -0.0871F));

		RWAAHAHAHA.addOrReplaceChild("sword7", CubeListBuilder.create().texOffs(80, 67).addBox(-0.5F, 1.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 0).addBox(-1.0F, -0.75F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 99).mirror().addBox(-0.5F, -18.75F, -2.0F, 1.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0189F, 4.2505F, 16.8889F, 1.2907F, -0.1765F, 0.1406F));

		PartDefinition shldrArmorL = upperbody.addOrReplaceChild("shldrArmorL", CubeListBuilder.create(), PartPose.offset(8.1523F, -15.3502F, -0.2527F));

		shldrArmorL.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 67).mirror().addBox(-3.0F, -2.0F, -6.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.1523F, 1.3502F, -0.7473F, -0.0151F, -0.8046F, -0.1101F));

		shldrArmorL.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(70, 48).mirror().addBox(-3.0F, -2.0F, -6.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.1523F, 1.3502F, -0.7473F, -0.0032F, -0.8006F, -0.5504F));

		PartDefinition shldrArmorR = upperbody.addOrReplaceChild("shldrArmorR", CubeListBuilder.create(), PartPose.offset(-8.1523F, -15.3502F, -0.2527F));

		shldrArmorR.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 67).addBox(-7.0F, -2.0F, -6.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1523F, 1.3502F, -0.7473F, -0.0151F, 0.8046F, 0.1101F));

		shldrArmorR.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(70, 48).addBox(-7.0F, -2.0F, -6.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1523F, 1.3502F, -0.7473F, -0.0032F, 0.8006F, 0.5504F));

		PartDefinition cape = upperbody.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(0.0F, -14.4555F, 4.4912F));

		cape.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(1, 15).addBox(-11.0F, -2.0F, -1.0F, 18.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.4555F, 1.5088F, 0.1745F, 0.0F, 0.0F));

		PartDefinition capelowerpart = cape.addOrReplaceChild("capelowerpart", CubeListBuilder.create(), PartPose.offset(0.0F, 20.3546F, 3.9807F));

		capelowerpart.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(37, 48).addBox(-10.0F, 9.0F, -1.0F, 16.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.8982F, -1.9101F, 0.3054F, 0.0F, 0.0F));

		PartDefinition LeftArm = upperbody.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(8.0F, -12.0F, -1.0F));

		PartDefinition ar3 = LeftArm.addOrReplaceChild("ar3", CubeListBuilder.create().texOffs(90, 102).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		ar3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 139).mirror().addBox(-1.5F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition ar4 = LeftArm.addOrReplaceChild("ar4", CubeListBuilder.create().texOffs(74, 99).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(90, 116).mirror().addBox(-2.0F, 1.5F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(0.999F, 8.499F, 0.001F));

		PartDefinition left_hand = ar4.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(65, 115).mirror().addBox(-0.9768F, 0.0F, -2.5F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.9768F, 7.5F, -0.5F));

		left_hand.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(21, 155).mirror().addBox(-2.0F, -0.5F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(1.1232F, 1.5F, 0.0F, 0.0F, 0.0F, -0.1309F));

		left_hand.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 155).mirror().addBox(0.0F, 0.0F, -2.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.0232F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition left_finger_4 = left_hand.addOrReplaceChild("left_finger_4", CubeListBuilder.create().texOffs(72, 128).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(65, 124).mirror().addBox(-3.0F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0232F, 4.0F, 2.0F));

		left_finger_4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(73, 139).mirror().addBox(-2.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.6F, 2.7F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition left_finger_1 = left_hand.addOrReplaceChild("left_finger_1", CubeListBuilder.create().texOffs(72, 128).mirror().addBox(-0.9768F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(65, 124).mirror().addBox(-2.9768F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 4.0F, -2.0F));

		left_finger_1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(73, 139).mirror().addBox(-2.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.6232F, 2.7F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition left_thumb = left_hand.addOrReplaceChild("left_thumb", CubeListBuilder.create().texOffs(72, 132).mirror().addBox(-1.0F, -0.5F, -0.55F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(72, 136).mirror().addBox(-1.0F, 1.5F, 0.45F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.9768F, 0.5F, -2.95F));

		left_thumb.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(75, 142).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.35F, -0.0873F, 0.0F, 0.0F));

		PartDefinition left_finger_2 = left_hand.addOrReplaceChild("left_finger_2", CubeListBuilder.create().texOffs(65, 124).mirror().addBox(-3.0F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(72, 128).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0232F, 4.0F, -0.7F));

		left_finger_2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(73, 139).mirror().addBox(-2.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.6F, 2.7F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition left_finger_3 = left_hand.addOrReplaceChild("left_finger_3", CubeListBuilder.create().texOffs(72, 128).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(65, 124).mirror().addBox(-3.0F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0232F, 4.0F, 0.7F));

		left_finger_3.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(73, 139).mirror().addBox(-2.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.6F, 2.7F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition RightArm = upperbody.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-8.0F, -12.0F, -1.0F));

		PartDefinition ar1 = RightArm.addOrReplaceChild("ar1", CubeListBuilder.create().texOffs(90, 102).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		ar1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 139).addBox(-5.5F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition ar2 = RightArm.addOrReplaceChild("ar2", CubeListBuilder.create().texOffs(74, 99).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 116).addBox(-2.0F, 1.5F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-0.999F, 8.499F, 0.001F));

		PartDefinition right_hand = ar2.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(65, 115).addBox(-1.0232F, 0.0F, -2.5F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.9768F, 7.5F, -0.5F));

		right_hand.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(21, 155).addBox(0.0F, -0.5F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.1232F, 1.5F, 0.0F, 0.0F, 0.0F, 0.1309F));

		right_hand.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 155).addBox(-1.0F, 0.0F, -2.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.0232F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition right_finger_4 = right_hand.addOrReplaceChild("right_finger_4", CubeListBuilder.create().texOffs(72, 128).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(65, 124).addBox(1.0F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0232F, 4.0F, 2.0F));

		right_finger_4.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(73, 139).addBox(-0.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.6F, 2.7F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition right_finger_1 = right_hand.addOrReplaceChild("right_finger_1", CubeListBuilder.create().texOffs(72, 128).addBox(-1.0232F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(65, 124).addBox(0.9768F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -2.0F));

		right_finger_1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(73, 139).addBox(-0.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.6232F, 2.7F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition right_thumb = right_hand.addOrReplaceChild("right_thumb", CubeListBuilder.create().texOffs(72, 132).addBox(-1.0F, -0.5F, -0.55F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(72, 136).addBox(-1.0F, 1.5F, 0.45F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.9768F, 0.5F, -2.95F));

		right_thumb.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(75, 142).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.35F, -0.0873F, 0.0F, 0.0F));

		PartDefinition right_finger_2 = right_hand.addOrReplaceChild("right_finger_2", CubeListBuilder.create().texOffs(65, 124).addBox(1.0F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(72, 128).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0232F, 4.0F, -0.7F));

		right_finger_2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(73, 139).addBox(-0.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.6F, 2.7F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition right_finger_3 = right_hand.addOrReplaceChild("right_finger_3", CubeListBuilder.create().texOffs(72, 128).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(65, 124).addBox(1.0F, 2.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0232F, 4.0F, 0.7F));

		right_finger_3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(73, 139).addBox(-0.5F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.6F, 2.7F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition neck = upperbody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(70, 60).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.5F, -1.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(74, 13).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(40, 121).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -3.5F, 0.0F));

		head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 117).addBox(-3.0F, -1.0F, -4.5F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 128).addBox(-3.0F, -1.0F, -4.5F, 6.0F, 5.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -3.0F, 0.3F));

		head.addOrReplaceChild("veil", CubeListBuilder.create().texOffs(26, 80).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -4.8F));

		PartDefinition crown = head.addOrReplaceChild("crown", CubeListBuilder.create().texOffs(70, 32).addBox(-3.0F, -0.808F, -1.7692F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 29).addBox(-0.5F, -1.808F, -1.7692F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 64).addBox(-0.5F, -3.808F, -1.7692F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(54, 34).addBox(2.0F, -2.808F, -1.7692F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(54, 34).mirror().addBox(-3.0F, -2.808F, -1.7692F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(70, 32).mirror().addBox(2.0F, -0.808F, -1.7692F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(38, 32).addBox(-4.0F, 0.192F, -1.2692F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(34, 58).addBox(-4.0F, -1.807F, -0.2702F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(26, 64).addBox(-4.0F, -0.807F, 1.7318F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(34, 58).mirror().addBox(4.0F, -1.807F, -0.2702F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(26, 64).mirror().addBox(4.0F, -0.807F, 1.7318F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.0F, -8.192F, -3.2308F));

		crown.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(46, 13).mirror().addBox(-6.0F, -1.0F, -1.2F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(2.8F, 1.192F, 4.7308F, 0.0F, -1.5708F, 0.0F));

		crown.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(46, 13).addBox(-2.0F, -1.0F, -1.2F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.8F, 1.192F, 4.7308F, 0.0F, 1.5708F, 0.0F));

		PartDefinition lower = main.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, -22.8571F, -3.5714F));

		PartDefinition skirt = lower.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(0, 58).addBox(-5.5F, -9.0F, 2.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(37, 36).addBox(-8.0F, -6.0F, 0.0F, 12.0F, 4.0F, 8.0F, new CubeDeformation(-0.001F))
		.texOffs(92, 60).addBox(-8.5F, 4.0F, -0.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(-0.005F))
		.texOffs(92, 60).mirror().addBox(4.5F, 4.0F, -0.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(-0.005F)).mirror(false)
		.texOffs(105, 30).addBox(-5.0F, -6.0F, -1.0F, 6.0F, 11.0F, 1.0F, new CubeDeformation(-0.004F)), PartPose.offset(2.0F, -0.1429F, -3.4286F));

		skirt.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(66, 79).addBox(-1.5F, -1.5F, -4.5F, 3.0F, 11.0F, 9.0F, new CubeDeformation(-0.005F)), PartPose.offsetAndRotation(-7.5F, -5.5F, 4.0F, 0.0F, 0.0F, 0.1309F));

		skirt.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(66, 79).mirror().addBox(-1.5F, -1.5F, -4.5F, 3.0F, 11.0F, 9.0F, new CubeDeformation(-0.005F)).mirror(false), PartPose.offsetAndRotation(3.5F, -5.5F, 4.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition RightLeg = lower.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, -0.1429F, 3.5714F));

		RightLeg.addOrReplaceChild("upperlg", CubeListBuilder.create().texOffs(32, 99).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		RightLeg.addOrReplaceChild("lowerlg", CubeListBuilder.create().texOffs(0, 96).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, -3.0F));

		PartDefinition LeftLeg = lower.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(4.0F, -0.1429F, 0.5714F));

		LeftLeg.addOrReplaceChild("upperlg2", CubeListBuilder.create().texOffs(32, 99).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		LeftLeg.addOrReplaceChild("lowerlg2", CubeListBuilder.create().texOffs(0, 96).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(EntityKingofSin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		MultiverseClientUtil.animateHead(this.head, netHeadYaw, headPitch);
		
		if(!MultiverseUtil.isMoving(entity))
		{
			limbSwing = 0.0F;
			limbSwingAmount = 0.0F;
		}
		
		this.animate(entity.idleAnimationState, KingofSinAnimation.KING_OF_SIN_IDLE, ageInTicks);
		this.animate(entity.crossAnimationState, KingofSinAnimation.KING_OF_SIN_ARM_CROSSED, ageInTicks);
		this.animateWalk(KingofSinAnimation.KING_OF_SIN_WALK_CROSSED, limbSwing, limbSwingAmount, 3.5F, 2.5F);
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
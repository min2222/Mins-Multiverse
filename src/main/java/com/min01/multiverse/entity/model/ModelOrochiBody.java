package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityOrochi;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelOrochiBody extends EntityModel<EntityOrochi>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "orochi_body"), "main");
	private final ModelPart root;

	public ModelOrochiBody(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition bone4 = root.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3333F, 6.7356F, 0.8481F, 0.0F, 0.0F, -3.1416F));

		bone4.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9167F, 0.7654F, 4.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0833F, 0.7654F, 4.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0833F, 0.7654F, -4.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0833F, 0.7654F, -4.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9167F, 0.7654F, -4.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0833F, 0.7654F, 6.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0833F, 0.7654F, 6.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9167F, 0.7654F, 6.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0833F, 0.7654F, 1.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0833F, 0.7654F, 1.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9167F, 0.7654F, 1.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9167F, 0.7654F, 4.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0833F, 0.7654F, 4.1522F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0833F, 0.7654F, -1.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0833F, 0.7654F, -1.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9167F, 0.7654F, -1.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9167F, 0.7654F, -1.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9167F, 0.7654F, -7.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0833F, 0.7654F, -7.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9167F, 0.7654F, -7.8478F, 2.7489F, 0.0F, 3.1416F));

		bone4.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0833F, 0.7654F, -7.8478F, 2.7489F, 0.0F, 3.1416F));

		PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -2.4991F, -6.9996F, 0.0F, 0.0F, 1.5708F));

		bone3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(-3, 6).mirror().addBox(-0.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 0.0F, 12.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(-1, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, 12.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(-3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 3.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 3.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(-3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 0.0F, 3.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(-3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 14.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 14.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(-3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 0.0F, 14.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(-3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 9.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 9.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(-3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 0.0F, 9.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 12.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 12.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(-1, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, 6.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 6.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(-3, 6).mirror().addBox(-0.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 0.0F, 6.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(-3, 6).mirror().addBox(-0.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(-1, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 2.7489F, 0.0F, -3.1416F));

		bone3.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(3, 10).mirror().addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.7489F, 0.0F, -3.1416F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -2.4991F, -6.9996F, 0.0F, 0.0F, -1.5708F));

		bone2.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 3.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 3.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 3.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 14.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 14.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 14.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 9.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 9.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 9.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		bone2.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(2.5F, -5.9991F, -6.9996F));

		bone.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 3.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 3.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 3.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 9.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 9.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 14.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 14.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 14.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(-3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 9.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 12.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 6.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(-3, 6).addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(-1, 10).addBox(0.5F, 0.0F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		bone.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(3, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 3.1416F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(EntityOrochi entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) 
	{
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
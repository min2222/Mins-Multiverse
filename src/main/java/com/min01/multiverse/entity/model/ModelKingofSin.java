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

		LeftArm.addOrReplaceChild("ar3", CubeListBuilder.create().texOffs(90, 102).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		LeftArm.addOrReplaceChild("ar4", CubeListBuilder.create().texOffs(74, 99).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.999F, 8.499F, 0.001F));

		PartDefinition RightArm = upperbody.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-8.0F, -12.0F, -1.0F));

		RightArm.addOrReplaceChild("ar1", CubeListBuilder.create().texOffs(90, 102).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		RightArm.addOrReplaceChild("ar2", CubeListBuilder.create().texOffs(74, 99).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.999F, 8.499F, 0.001F));

		PartDefinition neck = upperbody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(70, 60).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.5F, -1.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(74, 13).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 0.0F));

		head.addOrReplaceChild("veil", CubeListBuilder.create().texOffs(26, 80).addBox(-3.5F, -1.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -4.8F));

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

		crown.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(46, 13).mirror().addBox(-6.0F, -1.0F, -1.2F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(2.8F, 1.192F, 4.7308F, 0.0F, -1.5708F, 0.0F));

		crown.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 13).addBox(-2.0F, -1.0F, -1.2F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.8F, 1.192F, 4.7308F, 0.0F, 1.5708F, 0.0F));

		PartDefinition lower = main.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, -22.8571F, -3.5714F));

		lower.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(0, 58).addBox(-5.5F, -9.0F, 2.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(37, 36).addBox(-8.0F, -6.0F, 0.0F, 12.0F, 4.0F, 8.0F, new CubeDeformation(-0.001F))
		.texOffs(92, 60).addBox(-8.5F, 4.0F, -0.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(-0.005F))
		.texOffs(66, 79).mirror().addBox(2.0F, -7.0F, -0.5F, 3.0F, 11.0F, 9.0F, new CubeDeformation(-0.005F)).mirror(false)
		.texOffs(66, 79).addBox(-9.0F, -7.0F, -0.5F, 3.0F, 11.0F, 9.0F, new CubeDeformation(-0.005F))
		.texOffs(92, 60).mirror().addBox(4.5F, 4.0F, -0.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(-0.005F)).mirror(false)
		.texOffs(105, 30).addBox(-5.0F, -6.0F, -1.0F, 6.0F, 11.0F, 1.0F, new CubeDeformation(-0.004F)), PartPose.offset(2.0F, -0.1429F, -3.4286F));

		PartDefinition RightLeg = lower.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, -0.1429F, 3.5714F));

		RightLeg.addOrReplaceChild("upperlg", CubeListBuilder.create().texOffs(32, 99).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		RightLeg.addOrReplaceChild("lowerlg", CubeListBuilder.create().texOffs(0, 96).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, -3.0F));

		PartDefinition LeftLeg = lower.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(4.0F, -0.1429F, 0.5714F));

		LeftLeg.addOrReplaceChild("upperlg2", CubeListBuilder.create().texOffs(32, 99).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		LeftLeg.addOrReplaceChild("lowerlg2", CubeListBuilder.create().texOffs(0, 96).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
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
		this.animateWalk(KingofSinAnimation.KING_OF_SIN_WALK, limbSwing, limbSwingAmount, 3.5F, 2.5F);
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
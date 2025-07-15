package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.projectile.EntityScarletMagic;
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

public class ModelHand extends HierarchicalModel<EntityScarletMagic>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "hand"), "main");
	private final ModelPart root;

	public ModelHand(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 40.0F, 21.0F, 0.394F, 0.0149F, 0.0026F));

		PartDefinition hand = root.addOrReplaceChild("hand", CubeListBuilder.create().texOffs(0, 211).addBox(-24.0F, -6.0F, -26.5F, 48.0F, 12.0F, 53.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -38.884F, 25.9522F, -0.3927F, 0.0F, 0.0F));

		PartDefinition fingers = hand.addOrReplaceChild("fingers", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition finger1 = fingers.addOrReplaceChild("finger1", CubeListBuilder.create().texOffs(202, 211).addBox(-3.3763F, -0.8992F, -38.4405F, 8.0F, 8.0F, 39.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.5F, -4.5F, -26.5F, 1.3166F, -0.1176F, -0.1293F));

		PartDefinition finger2 = finger1.addOrReplaceChild("finger2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -1.0F, -38.0F, 0.1745F, 0.0F, 0.0F));

		finger2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(296, 250).addBox(-2.5F, -0.5F, -32.9F, 6.0F, 5.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3763F, 0.5225F, -0.1718F, 1.0472F, 0.0F, 0.0F));

		PartDefinition finger3 = fingers.addOrReplaceChild("finger3", CubeListBuilder.create().texOffs(202, 258).addBox(-4.0F, -1.5F, -38.0F, 8.0F, 7.0F, 39.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.8897F, -4.225F, -25.7645F, 1.4355F, -0.0371F, 0.0261F));

		PartDefinition finger4 = finger3.addOrReplaceChild("finger4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -38.0F, 0.5672F, 0.0F, 0.0F));

		finger4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 276).addBox(-2.5F, -0.5F, -39.6F, 6.0F, 5.0F, 40.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition finger5 = fingers.addOrReplaceChild("finger5", CubeListBuilder.create().texOffs(92, 276).addBox(-4.5F, -1.5F, -36.0F, 9.0F, 6.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.638F, -4.6446F, -25.2761F, 1.4778F, 0.0036F, 0.1033F));

		PartDefinition finger6 = finger5.addOrReplaceChild("finger6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -36.0F, 0.6545F, 0.0F, 0.0F));

		finger6.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(296, 211).addBox(-3.5F, 1.2F, -31.4F, 7.0F, 5.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0783F, -2.7313F, 0.5672F, 0.0F, 0.0F));

		PartDefinition finger7 = fingers.addOrReplaceChild("finger7", CubeListBuilder.create().texOffs(182, 304).addBox(-4.2376F, -0.1318F, -29.5581F, 8.0F, 6.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.9765F, -4.6398F, -26.8611F, 1.569F, 0.0646F, 0.2192F));

		PartDefinition finger8 = finger7.addOrReplaceChild("finger8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -29.0F, 0.48F, 0.0F, 0.0F));

		finger8.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(92, 318).addBox(-3.5F, 0.5F, -28.2F, 6.0F, 5.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2624F, 0.2899F, -2.2895F, 0.5672F, 0.0F, 0.0F));

		PartDefinition finger9 = fingers.addOrReplaceChild("finger9", CubeListBuilder.create().texOffs(296, 288).addBox(-6.2375F, -1.1318F, -29.5581F, 10.0F, 6.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.9025F, 0.8056F, 15.4453F, 0.0166F, -0.8417F, 0.7987F));

		PartDefinition finger10 = finger9.addOrReplaceChild("finger10", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -1.0F, -29.0F, 0.6545F, 0.0F, 0.0F));

		finger10.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 321).addBox(-5.5F, 0.5F, -25.1F, 8.0F, 5.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2625F, 0.2899F, -2.2895F, 0.5672F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(EntityScarletMagic entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
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
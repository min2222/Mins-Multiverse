package com.min01.multiverse.entity.model;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.misc.WormChain.Worm;
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
import net.minecraft.world.phys.Vec2;

public class ModelCreepeel extends HierarchicalModel<EntityCreepeel>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinsMultiverse.MODID, "creepeel"), "main");
	private final ModelPart root;
	public final ModelPart head;
	public final ModelPart back_body_small;
	public final ModelPart front_body_small;
	public final ModelPart back_body_medium;
	public final ModelPart front_body_medium;
	public final ModelPart back_body_large;
	public final ModelPart front_body_large;
	public final ModelPart back_body_huge;
	public final ModelPart front_body_huge;

	public ModelCreepeel(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.front_body_huge = this.head.getChild("front_body_huge");
		this.back_body_huge = this.front_body_huge.getChild("back_body_huge");
		this.front_body_large = this.back_body_huge.getChild("front_body_large");
		this.back_body_large = this.front_body_large.getChild("back_body_large");
		this.front_body_medium = this.back_body_large.getChild("front_body_medium");
		this.back_body_medium = this.front_body_medium.getChild("back_body_medium");
		this.front_body_small = this.back_body_medium.getChild("front_body_small");
		this.back_body_small = this.front_body_small.getChild("back_body_small");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 88).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition front_body_huge = head.addOrReplaceChild("front_body_huge", CubeListBuilder.create().texOffs(0, 60).addBox(-7.0F, -7.0F, 0.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition fin_main = front_body_huge.addOrReplaceChild("fin_main", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.0F, 7.0F, 8.0F, 0.7854F, 0.0F, 0.0F));

		fin_main.addOrReplaceChild("right_fin_main", CubeListBuilder.create().texOffs(108, 13).addBox(14.9F, -8.0F, -5.0F, 0.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_main.addOrReplaceChild("left_fin_main", CubeListBuilder.create().texOffs(108, 13).addBox(-0.1F, -8.0F, -5.0F, 0.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fin_huge = front_body_huge.addOrReplaceChild("fin_huge", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 17.0F, 36.0F, 0.48F, 0.0F, 0.0F));

		fin_huge.addOrReplaceChild("medium_top2_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(0.0F, -5.0F, -4.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -34.7718F, -10.706F, 2.1817F, 0.0F, 0.0F));

		PartDefinition back_body_huge = front_body_huge.addOrReplaceChild("back_body_huge", CubeListBuilder.create().texOffs(0, 60).addBox(-7.0F, -7.0F, 0.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 14.0F));

		PartDefinition fin_huge2 = back_body_huge.addOrReplaceChild("fin_huge2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 17.0F, 22.0F, 0.48F, 0.0F, 0.0F));

		fin_huge2.addOrReplaceChild("medium_top1_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(0.0F, -5.0F, -4.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -29.2309F, -0.0618F, 2.1817F, 0.0F, 0.0F));

		PartDefinition fin_medium2 = back_body_huge.addOrReplaceChild("fin_medium2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 18.5F, 23.0F, 0.48F, 0.0F, 0.0F));

		fin_medium2.addOrReplaceChild("medium_bottom_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(-0.999F, -22.4738F, -13.5431F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 2.0F));

		PartDefinition front_body_large = back_body_huge.addOrReplaceChild("front_body_large", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 14.0F));

		PartDefinition fin_large = front_body_large.addOrReplaceChild("fin_large", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 17.0F, 8.0F, 0.48F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("front_bottom_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.999F, -13.4511F, -2.0168F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_large.addOrReplaceChild("front_top_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.001F, -11.9399F, -7.6127F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.999F, -30.6264F, 13.5269F, 2.1817F, 0.0F, 0.0F));

		PartDefinition back_body_large = front_body_large.addOrReplaceChild("back_body_large", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

		PartDefinition fin_large2 = back_body_large.addOrReplaceChild("fin_large2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 17.0F, -4.0F, 0.48F, 0.0F, 0.0F));

		fin_large2.addOrReplaceChild("back_bottom_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.999F, -7.9101F, 8.6273F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		fin_large2.addOrReplaceChild("back_top_fin_large", CubeListBuilder.create().texOffs(112, 5).addBox(-0.001F, -9.2788F, -6.2274F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.999F, -22.4244F, 22.7857F, 2.1817F, 0.0F, 0.0F));

		PartDefinition front_body_medium = back_body_large.addOrReplaceChild("front_body_medium", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

		PartDefinition fin_medium = front_body_medium.addOrReplaceChild("fin_medium", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 18.5F, -15.0F, 0.48F, 0.0F, 0.0F));

		fin_medium.addOrReplaceChild("front_bottom_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(0.001F, -8.5484F, 17.5388F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 2.0F));

		fin_medium.addOrReplaceChild("front_top_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(-0.001F, -7.546F, -5.1062F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.999F, -17.5146F, 30.8502F, 2.1817F, 0.0F, 0.0F));

		PartDefinition back_body_medium = front_body_medium.addOrReplaceChild("back_body_medium", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition fin_medium3 = back_body_medium.addOrReplaceChild("fin_medium3", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 18.5F, -25.0F, 0.48F, 0.0F, 0.0F));

		fin_medium3.addOrReplaceChild("back_bottom_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(0.001F, -3.9309F, 26.4089F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 2.0F));

		fin_medium3.addOrReplaceChild("back_top_fin_medium", CubeListBuilder.create().texOffs(116, 0).addBox(-4.001F, -4.812F, -8.1925F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.001F, -13.8571F, 35.7106F, 2.1817F, 0.0F, 0.0F));

		PartDefinition front_body_small = back_body_medium.addOrReplaceChild("front_body_small", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition front_fin_small = front_body_small.addOrReplaceChild("front_fin_small", CubeListBuilder.create(), PartPose.offsetAndRotation(0.001F, -1.2625F, 8.4926F, 0.48F, 0.0F, 0.0F));

		front_fin_small.addOrReplaceChild("front_bottom_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(0.0F, 0.7464F, -9.963F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		front_fin_small.addOrReplaceChild("front_top_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(-0.001F, -7.9007F, -4.5511F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.3328F, -0.1132F, 2.1817F, 0.0F, 0.0F));

		PartDefinition back_body_small = front_body_small.addOrReplaceChild("back_body_small", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		back_body_small.addOrReplaceChild("fin_back", CubeListBuilder.create().texOffs(74, -20).addBox(0.0F, -8.0F, -10.0F, 0.0F, 16.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 17.0F));

		PartDefinition back_fin_small = back_body_small.addOrReplaceChild("back_fin_small", CubeListBuilder.create(), PartPose.offsetAndRotation(0.001F, -1.2625F, 0.4926F, 0.48F, 0.0F, 0.0F));

		back_fin_small.addOrReplaceChild("back_bottom_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(0.001F, -7.0463F, -1.2758F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.001F, 11.4867F, -1.5911F));

		back_fin_small.addOrReplaceChild("back_top_fin_small", CubeListBuilder.create().texOffs(120, -4).addBox(-0.001F, -8.7877F, -5.0129F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5258F, 7.4446F, 2.1817F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityCreepeel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		MultiverseClientUtil.animateHead(this.head, netHeadYaw, headPitch);
	    float partialTicks = ageInTicks - entity.tickCount;
	    float yBodyRot = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
	    float correction = yBodyRot - netHeadYaw;
	    float prevYaw = correction;
	    float prevPitch = headPitch;
	    Worm[] worms = new Worm[] { entity.wormFrontHuge, entity.wormBackHuge, entity.wormFrontLarge, entity.wormBackLarge, entity.wormFrontMedium, entity.wormBackMedium, entity.wormFrontSmall, entity.wormBackSmall };
	    Vec2[] worldRots = new Vec2[worms.length];
	    ModelPart[] bones = new ModelPart[] { this.front_body_huge, this.back_body_huge, this.front_body_large, this.back_body_large, this.front_body_medium, this.back_body_medium, this.front_body_small, this.back_body_small };
	    for(int i = 0; i < worms.length; i++)
	    {
	        worldRots[i] = worms[i].getRot(partialTicks);
	    }
	    for(int i = 0; i < worms.length; i++)
	    {
	        float localYaw = worldRots[i].y - prevYaw;
	        float localPitch = worldRots[i].x - prevPitch;
	        MultiverseClientUtil.animateHead(bones[i], localYaw, localPitch);
	        prevYaw = worldRots[i].y;
	        prevPitch = worldRots[i].x;
	    }
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
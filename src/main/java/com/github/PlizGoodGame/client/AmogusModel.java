package com.github.PlizGoodGame.client;

import com.github.PlizGoodGame.MinecraftTestMod;
import com.github.PlizGoodGame.entities.AmogusEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class AmogusModel<T extends AmogusEntity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinecraftTestMod.MOD_ID, "amogus"), "main");
    private final ModelPart controller;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart right_foot;
    private final ModelPart left_foot;

    public AmogusModel(ModelPart root) {
        this.controller = root.getChild("controller");
        this.body = this.controller.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.right_foot = this.controller.getChild("right_foot");
        this.left_foot = this.controller.getChild("left_foot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition controller = partdefinition.addOrReplaceChild("controller", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = controller.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -17.0F, -4.0F, 12.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-4.0F, -13.0F, 4.0F, 8.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 25).addBox(-4.0F, -15.0F, -6.0F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -7.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 8).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -7.0F, 0.0F));

        PartDefinition right_foot = controller.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -7.0F, 0.0F));

        PartDefinition left_foot = controller.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(24, 33).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -7.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(AmogusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        controller.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
package com.github.PlizGoodGame.client;

import com.github.PlizGoodGame.MinecraftTestMod;
import com.github.PlizGoodGame.entities.AmogusEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class AmogusRenderer extends MobRenderer<AmogusEntity, AmogusModel<AmogusEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MinecraftTestMod.MOD_ID, "textures/entity/amogus.png");

    public AmogusRenderer(EntityRendererProvider.Context context) {
        super(context, new AmogusModel<>(context.bakeLayer(AmogusModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AmogusEntity entity) {
        return TEXTURE;
    }
}
package com.github.PlizGoodGame.client;

import com.github.PlizGoodGame.entities.FireBallEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class FireBallRenderer extends ThrownItemRenderer<FireBallEntity> {

    public FireBallRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

//    @Override
//    public ResourceLocation getTextureLocation(FireBallEntity entity) {
//        // Можно использовать текстуру огненного шара
//        return new ResourceLocation(MinecraftTestMod.MOD_ID, "textures/entity/fire_ball.png");
//        // Или временно использовать текстуру снежка:
//        // return new ResourceLocation("minecraft", "textures/item/snowball.png");
//    }
}
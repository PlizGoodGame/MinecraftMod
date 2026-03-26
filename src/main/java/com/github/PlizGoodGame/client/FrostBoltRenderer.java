package com.github.PlizGoodGame.client;

import com.github.PlizGoodGame.entities.FrostBoltEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class FrostBoltRenderer extends ThrownItemRenderer<FrostBoltEntity> {

    public FrostBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
}

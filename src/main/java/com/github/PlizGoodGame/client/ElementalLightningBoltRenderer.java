package com.github.PlizGoodGame.client;

import com.github.PlizGoodGame.entities.ElementalLightningBoltEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ElementalLightningBoltRenderer extends ThrownItemRenderer<ElementalLightningBoltEntity> {

    public ElementalLightningBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
}

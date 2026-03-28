package com.github.PlizGoodGame.client;

import com.github.PlizGoodGame.MinecraftTestMod;
import com.github.PlizGoodGame.entities.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MinecraftTestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    //private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        //LOGGER.info("Клиентская часть мода {} загружена", MinecraftTestMod.MOD_ID);
    }

    // Регистрируем рендерер для сущности
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.FIRE_BALL.get(), FireBallRenderer::new);
        event.registerEntityRenderer(ModEntities.FROST_BOLT.get(), FrostBoltRenderer::new);
        event.registerEntityRenderer(ModEntities.AMOGUS.get(), AmogusRenderer::new);
        //LOGGER.info("Рендерер для огненного шара зарегистрирован");
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AmogusModel.LAYER_LOCATION, AmogusModel::createBodyLayer);
    }
}

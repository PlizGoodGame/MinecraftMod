package com.github.PlizGoodGame;

import com.github.PlizGoodGame.entities.ModEntities;
import com.github.PlizGoodGame.items.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(MinecraftTestMod.MOD_ID)
public class MinecraftTestMod
{
    public static final String MOD_ID = "minecrafttestmod";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TAB.register("example_tab",
            () -> CreativeModeTab.builder()
                    // Заголовок вкладки
                    .title(Component.translatable(MOD_ID))
                    // Иконка вкладки (какой предмет будет отображаться)
                    .icon(() -> Items.BEDROCK.getDefaultInstance())
                    // Порядок отображения (после какой вкладки)
                    .withTabsBefore(CreativeModeTabs.BUILDING_BLOCKS)
                    // Содержимое вкладки
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FIRE_BALL.get());
                        output.accept(ModItems.FROST_BOLT.get());
                    })
                    .build()
    );


    public MinecraftTestMod(@NotNull FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        CREATIVE_MODE_TAB.register(modEventBus);
        ModEntities.register(modEventBus);
        ModItems.register(modEventBus);

        // Подписываемся на события мода
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Подписываемся на события игры (сервер, игроки и т.д.)
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Вызывается один раз при загрузке мода
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Здесь можно добавить регистрацию рецептов крафта
        // Здесь можно добавить генерацию руд в мире
    }

    // Добавляем наши предметы на вкладки творческого режима
    private void addCreative(@NotNull BuildCreativeModeTabContentsEvent event)
    {
    }

}
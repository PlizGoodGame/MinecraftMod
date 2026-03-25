package com.github.PlizGoodGame;

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
    private static final Logger LOGGER = LogUtils.getLogger();

    // Регистрация блоков и предметов
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Наш первый блок
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f, 3.0f)  // Прочность и сопротивление взрыву
                    .requiresCorrectToolForDrops()  // Требует правильный инструмент
            ));

    // Предмет-блок для нашего блока (чтобы можно было ставить и собирать)
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
            () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TAB.register("example_tab",
            () -> CreativeModeTab.builder()
                    // Заголовок вкладки
                    .title(Component.translatable("itemGroup." + MOD_ID + ".example_tab"))
                    // Иконка вкладки (какой предмет будет отображаться)
                    .icon(() -> Items.BEDROCK.getDefaultInstance())
                    // Порядок отображения (после какой вкладки)
                    .withTabsBefore(CreativeModeTabs.BUILDING_BLOCKS)
                    // Содержимое вкладки
                    .displayItems((parameters, output) -> {
                        // Добавляем все наши предметы
                        output.accept(EXAMPLE_ITEM.get());
                        output.accept(EXAMPLE_BLOCK_ITEM.get());
                        // Можно добавить другие предметы
                        // output.accept(ModItems.ANOTHER_ITEM.get());
                    })
                    .build()
    );


    public MinecraftTestMod(@NotNull FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Регистрируем наши блоки и предметы
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TAB.register(modEventBus);

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
        // Добавляем блок на вкладку "Строительные блоки"
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.accept(EXAMPLE_BLOCK_ITEM);
            LOGGER.info("Блок добавлен на вкладку строительных блоков");
        }
    }

    // Событие при запуске сервера
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Сервер запущен! Мод {} готов к работе", MOD_ID);
    }

    // Клиентские события (отдельный класс для клиентской стороны)
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Клиентская часть мода {} загружена", MOD_ID);
            // Здесь можно добавить:
            // - Регистрацию моделей предметов
            // - Регистрацию специальных рендереров
            // - Настройку клавиш
        }
    }
}
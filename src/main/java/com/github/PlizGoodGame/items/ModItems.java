package com.github.PlizGoodGame.items;

import com.github.PlizGoodGame.MinecraftTestMod;
import com.github.PlizGoodGame.entities.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftTestMod.MOD_ID);

    // Параметры предмета:
    // - stacksTo(1) - не имеет стака (только 1 предмет в слоте)
    // - durability(0) - бесконечная прочность (0 = не ломается)
    // - rarity(Rarity.UNCOMMON) - редкий предмет (цвет названия)
    public static final RegistryObject<Item> FIRE_BALL = ITEMS.register("fire_ball",
            () -> new FireBallItem(new Item.Properties()
                    .stacksTo(1)           // Не имеет стака
                    .rarity(Rarity.COMMON) // Цвет названия (синий)
                    // durability не указываем, значит бесконечная прочность
            ));

    public static final RegistryObject<Item> FROST_BOLT = ITEMS.register("frost_bolt",
            () -> new FrostBoltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> ELEMENTAL_LIGHTNING_BOLT = ITEMS.register("elemental_lightning_bolt",
            () -> new ElementalLightningBoltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> AMOGUS_SPAWN_EGG = ITEMS.register("amogus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.AMOGUS,
                    0xF01111,
                    0x1A1FDF,
                    new Item.Properties()
            )
    );


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
package com.github.PlizGoodGame.items;

import com.github.PlizGoodGame.MinecraftTestMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
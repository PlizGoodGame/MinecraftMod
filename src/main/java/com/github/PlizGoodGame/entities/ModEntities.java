package com.github.PlizGoodGame.entities;

import com.github.PlizGoodGame.MinecraftTestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinecraftTestMod.MOD_ID);

    public static final RegistryObject<EntityType<FireBallEntity>> FIRE_BALL =
            ENTITIES.register("fire_ball",
                    () -> EntityType.Builder.<FireBallEntity>of(FireBallEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(7)
                            .build("fire_ball"));

    public static final RegistryObject<EntityType<FrostBoltEntity>> FROST_BOLT =
            ENTITIES.register("frost_bolt",
                    () -> EntityType.Builder.<FrostBoltEntity>of(FrostBoltEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(4)
                            .build("frost_bolt"));

    public static final RegistryObject<EntityType<AmogusEntity>> AMOGUS =
            ENTITIES.register("amogus",
                    () -> EntityType.Builder.of(AmogusEntity::new, MobCategory.MONSTER)
                        .sized(1.0f, 1.5f)  // Width, Height
                        .build(new ResourceLocation(MinecraftTestMod.MOD_ID, "amogus").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}

package com.github.PlizGoodGame.effects;

import com.github.PlizGoodGame.MinecraftTestMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MinecraftTestMod.MOD_ID);

    public static final RegistryObject<MobEffect> CHILL =
            EFFECTS.register("chill", ChillEffect::new);

    public static final RegistryObject<MobEffect> FREEZE =
            EFFECTS.register("freeze", FreezeEffect::new);

    public static final RegistryObject<MobEffect> SHOCK =
            EFFECTS.register("shock", ShockEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}

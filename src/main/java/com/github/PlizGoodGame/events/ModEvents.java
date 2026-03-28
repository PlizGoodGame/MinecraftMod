package com.github.PlizGoodGame.events;

import com.github.PlizGoodGame.effects.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "minecrafttestmod")
public class ModEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(ModEffects.SHOCK.get())) {
            int amplifier = entity.getEffect(ModEffects.SHOCK.get()).getAmplifier();
            // Увеличиваем урон на 25% + 25% за каждый уровень
            float multiplier = 1.25F + (amplifier * 0.25F);
            event.setAmount(event.getAmount() * multiplier);
        }
    }
}

package com.github.PlizGoodGame.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ShockEffect extends MobEffect {

    public ShockEffect() {
        super(
                MobEffectCategory.HARMFUL,
                0xE8F25A
        );
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Частицы (только на клиенте)
        if (entity.level().isClientSide) {
            for (int i = 0; i < 2; i++) {
                double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth();
                double y = entity.getY() + entity.getRandom().nextDouble() * entity.getBbHeight();
                double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth();

                entity.level().addParticle(ParticleTypes.SNOWFLAKE, x, y, z, 0, 0, 0);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Возвращаем true, чтобы эффект тикал каждый тик
        return true;
    }
}

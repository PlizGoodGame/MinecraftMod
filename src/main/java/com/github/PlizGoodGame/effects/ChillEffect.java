package com.github.PlizGoodGame.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ChillEffect extends MobEffect {

    public ChillEffect() {

        super(
                MobEffectCategory.HARMFUL,  // Тип: HARMFUL (вредный), BENEFICIAL (полезный), NEUTRAL (нейтральный)
                0x9FDEED
        );


        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68070635", // UUID для модификатора
                -0.3,                                    // Значение (-50%)
                AttributeModifier.Operation.MULTIPLY_TOTAL // Операция умножения
        );

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68070636",
                -0.3,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Этот метод вызывается каждый тик (20 раз в секунду)

        // Проверяем, не находится ли сущность в лаве
        if (entity.isOnFire()) {
            // Снимаем эффект, если в лаве
            entity.removeEffect(this);
        }

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

package com.github.PlizGoodGame.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FreezeEffect extends MobEffect {

    public FreezeEffect() {

        super(
                MobEffectCategory.HARMFUL,  // Тип: HARMFUL (вредный), BENEFICIAL (полезный), NEUTRAL (нейтральный)
                0x008BFF
        );

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68070635",
                -1,
                AttributeModifier.Operation.MULTIPLY_TOTAL // Операция умножения
        );

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68070636",
                -1,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Снимаем эффект, если цель горит
        if (entity.isOnFire()) {
            entity.removeEffect(this);
            return;
        }

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

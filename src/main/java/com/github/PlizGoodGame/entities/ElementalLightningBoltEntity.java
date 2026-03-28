package com.github.PlizGoodGame.entities;

import com.github.PlizGoodGame.effects.ModEffects;
import com.github.PlizGoodGame.items.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ElementalLightningBoltEntity extends ThrowableItemProjectile {

    private final int lifetime = 100; // Время жизни шара (5 секунд)
    private int age = 0;

    public ElementalLightningBoltEntity(EntityType<? extends ElementalLightningBoltEntity> type, Level level) {
        super(type, level);
    }

    public ElementalLightningBoltEntity(net.minecraft.world.level.Level level, LivingEntity shooter) {
        super(ModEntities.ELEMENTAL_LIGHTNING_BOLT.get(), shooter, level);
        this.setNoGravity(true);
    }

    protected Item getDefaultItem() {
        return ModItems.ELEMENTAL_LIGHTNING_BOLT.get();
    }

    @Override
    public void tick() {
        super.tick();

        age++;

        // Удаляем после истечения времени жизни
        if (age >= lifetime) {
            this.discard();
            return;
        }

        // Добавляем частицы для следа
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                    this.getX(), this.getY(), this.getZ(),
                    0, 0, 0);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();

        if (entity instanceof LivingEntity livingEntity) {
            entity.hurt(damageSources().lightningBolt(), 5.0F);
            float chance = 0.15F;
            if (this.random.nextFloat() < chance)
            {
                livingEntity.addEffect(new MobEffectInstance(
                        ModEffects.SHOCK.get(),
                        100,
                        0
                ));
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        SoundEvents.GLASS_BREAK, SoundSource.HOSTILE, 1.0F, 0.5F);
            }
        }

        // Эффекты при попадании
        if (!this.level().isClientSide) {
            // Взрывные частицы
            for (int i = 0; i < 10; i++) {
                this.level().addParticle(ParticleTypes.POOF,
                        entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.5,
                        (this.random.nextDouble() - 0.5) * 0.5,
                        (this.random.nextDouble() - 0.5) * 0.5);
            }

            // Звук попадания
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.HOSTILE, 1.0F, 1.0F);
        }

        // Удаляем шар после попадания
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        // При попадании в блок - удаляем шар
        if (!this.level().isClientSide) {
            // Частицы дыма
            for (int i = 0; i < 5; i++) {
                this.level().addParticle(ParticleTypes.SMOKE,
                        result.getLocation().x, result.getLocation().y, result.getLocation().z,
                        (this.random.nextDouble() - 0.5) * 0.3,
                        (this.random.nextDouble() - 0.5) * 0.3,
                        (this.random.nextDouble() - 0.5) * 0.3);
            }

            // Звук удара о блок
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.TRIDENT_RIPTIDE_3, SoundSource.HOSTILE, 0.5F, 1.0F);

            // Удаляем шар
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        // Удаление уже обработано в onHitEntity и onHitBlock
    }
}

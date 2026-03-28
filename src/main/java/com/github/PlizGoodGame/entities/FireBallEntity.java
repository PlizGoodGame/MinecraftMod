package com.github.PlizGoodGame.entities;

import com.github.PlizGoodGame.items.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class FireBallEntity extends ThrowableItemProjectile {

    private final int lifetime = 100; // Время жизни шара (5 секунд)
    private int age = 0;

    public FireBallEntity(EntityType<? extends FireBallEntity> type, Level level) {
        super(type, level);
    }

    public FireBallEntity(Level level, LivingEntity shooter) {
        super(ModEntities.FIRE_BALL.get(), shooter, level);
        this.setNoGravity(true); // Летит по прямой, не падает
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FIRE_BALL.get();
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
            this.level().addParticle(ParticleTypes.FLAME,
                    this.getX(), this.getY(), this.getZ(),
                    0, 0, 0);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();

        // Наносим урон
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.isOnFire()) {
                entity.hurt(damageSources().onFire(), 5.0F);
            }
            else {
                entity.hurt(damageSources().onFire(), 2.0F);
            }
            entity.setSecondsOnFire(5);
        }

        // Эффекты при попадании
        if (!this.level().isClientSide) {
            // Взрывные частицы
            for (int i = 0; i < 10; i++) {
                this.level().addParticle(ParticleTypes.EXPLOSION,
                        entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.5,
                        (this.random.nextDouble() - 0.5) * 0.5,
                        (this.random.nextDouble() - 0.5) * 0.5);
            }

            // Звук попадания
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.0F, 1.0F);
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
                    SoundEvents.FIRE_EXTINGUISH, SoundSource.HOSTILE, 0.5F, 1.0F);

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
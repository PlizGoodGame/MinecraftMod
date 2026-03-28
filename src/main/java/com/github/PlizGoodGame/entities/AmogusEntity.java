package com.github.PlizGoodGame.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;

public class AmogusEntity extends Monster {

    protected AmogusEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        //this.setBoundingBox(new AABB(-0.5, 0, -0.5, 0.5, 1.5, 0.5));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // Add movement goals
        this.goalSelector.addGoal(0, new FloatGoal(this)); // Swim
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        // Target goals - attack players
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.1D);
    }

    @Override
    public void tick() {
        super.tick();

        // Optional: Add particle effects when moving fast
        if (this.level().isClientSide && this.getDeltaMovement().length() > 0.3) {
            for (int i = 0; i < 2; i++) {
                this.level().addParticle(net.minecraft.core.particles.ParticleTypes.POOF,
                        this.getX(), this.getY() + 0.5, this.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.1,
                        this.random.nextDouble() * 0.1,
                        (this.random.nextDouble() - 0.5) * 0.1);
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        // Optional: Custom step sounds
        super.playStepSound(pos, state);
    }

    @Override
    public boolean isAggressive() {
        return true; // Always aggressive
    }
}

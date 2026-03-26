package com.github.PlizGoodGame.items;

import com.github.PlizGoodGame.entities.FireBallEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.List;

public class FireBallItem extends Item {

    private static final int COOLDOWN_TICKS = 10; // 1 секунда кулдауна

    public FireBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Создаем огненный шар
            FireBallEntity fireBall = new FireBallEntity(level, player);

            // Настраиваем полет
            fireBall.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,      // Погрешность по оси X
                    1.5F,      // Скорость полета
                    1.0F       // Разброс
            );

            // Добавляем шар в мир
            level.addFreshEntity(fireBall);
        }

        // Устанавливаем кулдаун
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        // Возвращаем успешное использование (предмет не тратится, т.к. бесконечная прочность)
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        //tooltip.add(Component.literal("§7Огненный шар, который поджигает врагов"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
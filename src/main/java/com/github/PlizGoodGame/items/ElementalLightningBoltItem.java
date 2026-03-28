package com.github.PlizGoodGame.items;

import com.github.PlizGoodGame.entities.ElementalLightningBoltEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ElementalLightningBoltItem extends Item {

    private static final int COOLDOWN_TICKS = 15;

    public ElementalLightningBoltItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Создаем огненный шар
            ElementalLightningBoltEntity lightningBolt = new ElementalLightningBoltEntity(level, player);

            // Настраиваем полет
            lightningBolt.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,      // Погрешность по оси X
                    5F,      // Скорость полета
                    0F       // Разброс
            );

            // Добавляем шар в мир
            level.addFreshEntity(lightningBolt);
        }

        // Устанавливаем кулдаун
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        // Возвращаем успешное использование (предмет не тратится, т.к. бесконечная прочность)
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        //.add(Component.literal("§7Ледяная стрела, которая замедляет врагов"));;
        super.appendHoverText(stack, level, tooltip, flag);
    }
}

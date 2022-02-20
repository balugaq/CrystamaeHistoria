package net.guizhanss.minecraft.crystamaehistoria.utils;

import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

@UtilityClass
public class GuiItems {
    @Nonnull
    public static ItemStack getBackButton(@Nonnull Player p) {
        return ChestMenuUtils.getBackButton(
            p,
            "",
            "&7返回 Slimefun 指南"
        );
    }
}

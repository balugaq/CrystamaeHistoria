package io.github.sefiraat.crystamaehistoria.utils.theme;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

@UtilityClass
public class GuiElements {

    public static final CustomItemStack MENU_BACKGROUND = new CustomItemStack(
        Material.BLACK_STAINED_GLASS_PANE,
        " "
    );

    public static final CustomItemStack MENU_BACKGROUND_INPUT = new CustomItemStack(
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        ChatColor.BLUE + "输入"
    );

    public static final CustomItemStack MENU_STAVE_INPUT = new CustomItemStack(
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        ChatColor.BLUE + "在此处放入法杖"
    );

    public static final CustomItemStack MENU_REMOVE_PLATES = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        ChatColor.BLUE + "移除魔法板"
    );

    public static final CustomItemStack MENU_SAVE_STAVE = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE,
        ChatColor.BLUE + "保存法杖设置"
    );

    public static final CustomItemStack MENU_BACKGROUND_OUTPUT = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        ChatColor.RED + "输出"
    );

    public static final CustomItemStack MENU_DIVIDER = new CustomItemStack(
        Material.LIME_STAINED_GLASS_PANE,
        " "
    );

    public static final CustomItemStack TIER_INDICATOR_1 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_1.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "T1"
    );

    public static final CustomItemStack TIER_INDICATOR_2 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_2.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "T2"
    );

    public static final CustomItemStack TIER_INDICATOR_3 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_3.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "T3"
    );

    public static final CustomItemStack TIER_INDICATOR_4 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_4.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "T4"
    );

    public static final CustomItemStack TIER_INDICATOR_5 = new CustomItemStack(
        Skulls.GUI_TIER_NUMBER_5.getPlayerHead(),
        ThemeType.CLICK_INFO.getColor() + "T5"
    );

    public static ItemStack getUniqueStoryIcon(Material material) {
        return ThemeType.themedItemStack(
            material,
            ThemeType.RARITY_UNIQUE,
            ThemeType.toTitleCase(material.toString()),
            "该故事已被发掘"
        );
    }

    public static ItemStack getStoryNotUnlockedIcon(Material material) {
        return ThemeType.themedItemStack(
            Material.BARRIER,
            ThemeType.RESEARCH,
            ThemeType.toTitleCase(material.toString()),
            MessageFormat.format("{0}{1}已锁定", ThemeType.RESEARCH.getColor(), ChatColor.BOLD),
            "该故事还没有解锁",
            "当你使用记录者首次发掘",
            "指定方块的故事时才能解锁"
        );
    }

    public static ItemStack getSpellNotUnlockedIcon(String id) {
        return ThemeType.themedItemStack(
            Material.BARRIER,
            ThemeType.RESEARCH,
            ThemeType.toTitleCase(id),
            MessageFormat.format("{0}{1}已锁定", ThemeType.RESEARCH.getColor(), ChatColor.BOLD),
            "该法术还没有被解锁",
            "当你在液化池中首次",
            "充能魔法板时才能解锁法术"
        );
    }

    public static CustomItemStack getSpellSlotPane(SpellSlot spellSlot) {
        return new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            ChatColor.GRAY + "法术: " + ThemeType.toTitleCase(spellSlot.name())
        );
    }

    public static CustomItemStack getDirectionalSlotPane(BlockFace blockFace, boolean active) {
        Material material = active ? Material.RED_STAINED_GLASS_PANE : Material.GREEN_STAINED_GLASS_PANE;
        return new CustomItemStack(
            material,
            ChatColor.GRAY + "设置方向: " + blockFace.name()
        );
    }
}

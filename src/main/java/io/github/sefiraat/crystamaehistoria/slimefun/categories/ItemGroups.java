package io.github.sefiraat.crystamaehistoria.slimefun.categories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public final class ItemGroups {

    public static final NestedItemGroup MAIN = new NestedItemGroup(CrystamaeHistoria.getKeys().getCatMain(), new CustomItemStack(
        new ItemStack(Material.AMETHYST_CLUSTER),
        ThemeType.MAIN.getChatColor() + "水晶编年史"
    ));
    public static final SubItemGroup MECHANISMS = new SubItemGroup(CrystamaeHistoria.getKeys().getCatMechanical(), MAIN, new CustomItemStack(
        new ItemStack(Material.DEEPSLATE_TILE_SLAB),
        ThemeType.MAIN.getChatColor() + "装置"
    ));
    public static final SubItemGroup CRYSTALS = new SubItemGroup(CrystamaeHistoria.getKeys().getCatCrystal(), MAIN, new CustomItemStack(
        new ItemStack(Material.AMETHYST_CLUSTER),
        ThemeType.MAIN.getChatColor() + "水晶"
    ));
    public static final SubItemGroup TOOLS = new SubItemGroup(CrystamaeHistoria.getKeys().getCatTools(), MAIN, new CustomItemStack(
        new ItemStack(Material.STICK),
        ThemeType.MAIN.getChatColor() + "法杖与工具"
    ));

    public static void setup() {
        MAIN.register(CrystamaeHistoria.inst());
        MECHANISMS.register(CrystamaeHistoria.inst());
        CRYSTALS.register(CrystamaeHistoria.inst());
        TOOLS.register(CrystamaeHistoria.inst());
    }

}

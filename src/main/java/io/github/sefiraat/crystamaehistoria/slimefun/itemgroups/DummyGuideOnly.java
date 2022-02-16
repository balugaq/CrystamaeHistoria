package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class DummyGuideOnly {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_GUIDE_DUMMY",
        new ItemStack(Material.BOOK),
        ThemeType.MECHANISM,
        "指南",
        "这只是一个指南物品",
        "由于魔法水晶编年史中",
        "部分物品的制作方式",
        "与其他附属插件不同",
        "你可以通过查看指南来了解详情"
    );
    public static final RecipeType TYPE = new RecipeType(Keys.GUIDE_ONLY, STACK);
}

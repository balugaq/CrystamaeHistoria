package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class DummyLiquefactionBasinCrafting {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_LIQUEFACTION_BASIN_DUMMY_CRAFTING",
        new ItemStack(Material.CAULDRON),
        ThemeType.MECHANISM,
        "液化池",
        "在液化池中投入足够数量的魔法水晶",
        "然后投入指定的物品来进行合成"
    );
    public static final RecipeType TYPE = new RecipeType(Keys.LIQUEFACTION_DUMMY_CRAFTING, STACK);
}

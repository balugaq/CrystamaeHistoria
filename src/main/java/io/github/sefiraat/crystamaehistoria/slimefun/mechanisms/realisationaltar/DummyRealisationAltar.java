package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class DummyRealisationAltar {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_REALISATION_ALTAR_DUMMY_CRYSTAL",
        new ItemStack(Material.CHISELED_DEEPSLATE),
        ThemeType.MECHANISM,
        "现实祭坛",
        "将有故事的方块放入现实祭坛",
        "现实祭坛会提取出方块故事中蕴含的魔法元素",
        "并将其转化为物理形态——魔法水晶簇",
        "破坏现实祭坛周围的魔法水晶簇后",
        "可以获得其中包含的不同类型的魔法水晶"
    );
    public static final RecipeType TYPE = new RecipeType(Keys.REALISATION_DUMMY_CRYSTAL, STACK);
}

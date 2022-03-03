package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedBeacon;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedFertilityPharo;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedHarvester;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedSeaBreeze;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedTime;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedWeather;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Exalted {

    @Getter
    private static ExaltedBeacon exaltedBeacon;
    @Getter
    private static ExaltedFertilityPharo exaltedFertilityPharo;
    @Getter
    private static ExaltedHarvester exaltedHarvester;
    @Getter
    private static ExaltedTime exaltedDawn;
    @Getter
    private static ExaltedTime exaltedDusk;
    @Getter
    private static ExaltedWeather exaltedSun;
    @Getter
    private static ExaltedWeather exaltedStorm;
    @Getter
    private static ExaltedSeaBreeze exaltedSeaBreeze;

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack amalgamateIngotMythical = Materials.getAmalgamateIngotMythical().getItem();
        final ItemStack amalgamateDustMythical = Materials.getAmalgamateDustMythical().getItem();

        // Exaltation Beacon
        RecipeItem exaltedBeaconRecipe = new RecipeItem(
            amalgamateIngotMythical,
            StoryType.ELEMENTAL, 500,
            StoryType.HUMAN, 500,
            StoryType.PHILOSOPHICAL, 500,
            Exalted::isMaxStoryRank
        );
        exaltedBeacon = new ExaltedBeacon(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_BEACON",
                new ItemStack(Material.BEACON),
                ThemeType.EXALTED,
                "尊贵的信标",
                "一个信标，内部拥有难以置信的力量",
                "需要放置在尊贵展台上才能使用",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 故事等级 达到 荣誉教授"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedBeaconRecipe.getDisplayRecipe()
        );

        // Fertility Pharo
        RecipeItem exaltedFertilityPharoRecipe = new RecipeItem(
            amalgamateIngotMythical,
            StoryType.ELEMENTAL, 500,
            StoryType.ANIMAL, 500,
            StoryType.CELESTIAL, 500,
            Exalted::isMaxStoryRank
        );
        exaltedFertilityPharo = new ExaltedFertilityPharo(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_FERTILITY_PHARO",
                new ItemStack(Material.DIAMOND_BLOCK),
                ThemeType.EXALTED,
                "尊贵的喂养机",
                "一个魔法构造，可以喂养周围20x20范围内的动物",
                "需要放置在尊贵展台上才能使用",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 故事等级 达到 荣誉教授"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedFertilityPharoRecipe.getDisplayRecipe()
        );

        // Exalted Harvester
        RecipeItem exaltedHarvesterRecipe = new RecipeItem(
            amalgamateIngotMythical,
            StoryType.ELEMENTAL, 500,
            StoryType.HISTORICAL, 500,
            StoryType.VOID, 500,
            Exalted::isMaxSpellRank
        );
        exaltedHarvester = new ExaltedHarvester(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_HARVESTER",
                new ItemStack(Material.HAY_BLOCK),
                ThemeType.EXALTED,
                "尊贵的收割机",
                "一个魔法构造，可以收获9x9范围内的所有作物",
                "需要放置在尊贵展台上才能使用",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 法术等级 达到 宗师级魔法师"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedHarvesterRecipe.getDisplayRecipe()
        );

        // Exalted Dawn
        RecipeItem exaltedDawnRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            StoryType.CELESTIAL, 250,
            player -> player.getWorld().isDayTime()
        );
        exaltedDawn = new ExaltedTime(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_DAWN",
                new ItemStack(Material.YELLOW_WOOL),
                ThemeType.EXALTED,
                "尊贵的黎明",
                "一个魔法构造，像太阳一样耀眼",
                "让玩家时间固定在白天（不会影响实际时间）",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 在白天合成"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedDawnRecipe.getDisplayRecipe(),
            6000
        );

        // Exalted Dusk
        RecipeItem exaltedDuskRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            StoryType.VOID, 250,
            player -> !player.getWorld().isDayTime()
        );
        exaltedDusk = new ExaltedTime(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_DUSK",
                new ItemStack(Material.BLACK_WOOL),
                ThemeType.EXALTED,
                "尊贵的黄昏",
                "一个魔法构造，像新月一样闪耀",
                "让玩家时间固定在夜晚（不会影响实际时间）",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 在夜间合成"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedDuskRecipe.getDisplayRecipe(),
            18000
        );

        // Exalted Sun
        RecipeItem exaltedSunRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.ALCHEMICAL, 250,
            StoryType.CELESTIAL, 250,
            player -> player.getWorld().isClearWeather()
        );
        exaltedSun = new ExaltedWeather(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_SUN",
                new ItemStack(Material.MAGMA_BLOCK),
                ThemeType.EXALTED,
                "尊贵的太阳",
                "一个散发着太阳的光辉的魔法构造",
                "让玩家天气变为晴天（不会影响实际天气）",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 需要在晴天合成"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedSunRecipe.getDisplayRecipe(),
            WeatherType.CLEAR
        );

        // Exalted Storm
        RecipeItem exaltedStormRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.ALCHEMICAL, 250,
            StoryType.VOID, 250,
            player -> !player.getWorld().isClearWeather()
        );
        exaltedStorm = new ExaltedWeather(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_STORM",
                new ItemStack(Material.GRAY_WOOL),
                ThemeType.EXALTED,
                "尊贵的风暴",
                "一个散发着风暴的破坏力的魔法构造",
                "让玩家天气变为雨天（不会影响实际天气）",
                "",
                ThemeType.CLICK_INFO.getColor() + "需要: 在雨天合成"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedStormRecipe.getDisplayRecipe(),
            WeatherType.DOWNFALL
        );

        // Exalted Sea Breeze
        RecipeItem exaltedSeeBreezeRecipe = new RecipeItem(
            new ItemStack(Material.HEART_OF_THE_SEA),
            StoryType.ELEMENTAL, 125,
            StoryType.ALCHEMICAL, 200,
            StoryType.CELESTIAL, 150,
            player -> player.getWorld().getBiome(player.getLocation()) == Biome.BEACH
        );
        exaltedSeaBreeze = new ExaltedSeaBreeze(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_SEA_BREEZE",
                new ItemStack(Material.TUBE_CORAL_BLOCK),
                ThemeType.EXALTED,
                "Exalted Sea Breeze",
                "A magical construct that brings",
                "the sea air to nearby blocks, weathering",
                "and oxidizing them.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Crafted on a beach"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedSeeBreezeRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        exaltedBeacon.register(plugin);
        exaltedFertilityPharo.register(plugin);
        exaltedHarvester.register(plugin);
        exaltedDawn.register(plugin);
        exaltedDusk.register(plugin);
        exaltedSun.register(plugin);
        exaltedStorm.register(plugin);
        exaltedSeaBreeze.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(exaltedBeacon, exaltedBeaconRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedFertilityPharo, exaltedFertilityPharoRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedHarvester, exaltedHarvesterRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedDawn, exaltedDawnRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedDusk, exaltedDuskRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedSun, exaltedSunRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedStorm, exaltedStormRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedSeaBreeze, exaltedSeeBreezeRecipe);
    }

    private static boolean isMaxStoryRank(Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    private static boolean isMaxSpellRank(Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }
}

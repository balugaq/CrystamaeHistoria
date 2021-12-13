package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.DummyGuideOnly;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.DummyItemGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.MainFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.SpellCollectionFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.StoryCollectionFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.magicpaintbrush.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public final class ItemGroups {

    public static final DummyItemGroup DUMMY_ITEM_GROUP = new DummyItemGroup(
        Keys.newKey("dummy"),
        new CustomItemStack(
            new ItemStack(Material.FIRE_CHARGE),
            ThemeType.MAIN.getColor() + "Dummy Crystamae Historia"
        )
    );
    public static final MainFlexGroup MAIN = new MainFlexGroup(
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getColor() + "魔法水晶编年史(CrystamaeHistoria)"
        )
    );
    public static final DummyItemGroup MECHANISMS = new DummyItemGroup(
        Keys.newKey("mechanisms"),
        new CustomItemStack(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeType.MAIN.getColor() + "装置"
        )
    );
    public static final DummyItemGroup CRYSTALS = new DummyItemGroup(
        Keys.newKey("crystals"),
        new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getColor() + "魔法水晶"
        )
    );
    public static final DummyItemGroup TOOLS = new DummyItemGroup(
        Keys.newKey("tools"),
        new CustomItemStack(
            new ItemStack(Material.STICK),
            ThemeType.MAIN.getColor() + "法杖与工具"
        )
    );
    public static final DummyItemGroup PAINTBRUSHES = new DummyItemGroup(
        Keys.newKey("paintbrushes"),
        new CustomItemStack(
            MagicPaintbrush.getTippedBrush(DyeColor.WHITE, true),
            ThemeType.MAIN.getColor() + "魔法画笔"
        )
    );
    public static final DummyItemGroup GADGETS = new DummyItemGroup(
        Keys.newKey("gadgets"),
        new CustomItemStack(
            new ItemStack(Material.REDSTONE_LAMP),
            ThemeType.MAIN.getColor() + "魔法科技道具"
        )
    );
    public static final DummyItemGroup MATERIALS = new DummyItemGroup(
        Keys.newKey("materials"),
        new CustomItemStack(
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.MAIN.getColor() + "原材料"
        )
    );
    public static final DummyItemGroup GUIDE = new DummyItemGroup(
        Keys.newKey("guide"),
        new CustomItemStack(
            new ItemStack(Material.BOOK),
            ThemeType.MAIN.getColor() + "指南"
        )
    );
    public static final StoryCollectionFlexGroup STORY_COLLECTION = new StoryCollectionFlexGroup(
        Keys.newKey("story_collection"),
        new CustomItemStack(
            new ItemStack(Material.KNOWLEDGE_BOOK),
            ThemeType.MAIN.getColor() + "故事集"
        )
    );
    public static final SpellCollectionFlexGroup SPELL_COLLECTION = new SpellCollectionFlexGroup(
        Keys.newKey("spell_collection"),
        new CustomItemStack(
            new ItemStack(Material.KNOWLEDGE_BOOK),
            ThemeType.MAIN.getColor() + "法术集"
        )
    );


    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        /*
        These items are for the misc guide which serves to inform players
        how the more abstract mechanics work where a true recipe doesn't
        suit or would be detrimental to the process.
         */

        // Chronicler
        SlimefunItem guideChronicler = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_CHRONICLER",
                new ItemStack(Material.DEEPSLATE_TILE_SLAB),
                ThemeType.GUIDE,
                "指南: 记录者",
                "你可以在记录者中放置一个方块",
                "记录者会\"发掘\"方块中含有的故事",
                "故事的类型取决于方块的类型",
                "而方块数量则是随机的，但随机范围与方块等级有关",
                "",
                "记录者只会发掘至少T1的方块",
                "",
                "当记录者完成发掘后，记录者上方的悬浮方块会消失"
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Realisation
        SlimefunItem guideRealisation = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_REALISATION",
                new ItemStack(Material.CHISELED_DEEPSLATE),
                ThemeType.GUIDE,
                "指南: 现实祭坛",
                "将一个已被发掘的方块放入现实祭坛中",
                "祭坛会开始从方块中提取魔法能量",
                "这会在周围生成紫水晶(必须同一高度)",
                "",
                "魔法水晶簇会缓慢生长，直到魔法能量被完全提取出来",
                "这时你可以破坏魔法水晶簇来获取魔法水晶"
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Liquefaction
        SlimefunItem guideLiquefaction = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_LIQUEFACTION",
                new ItemStack(Material.CAULDRON),
                ThemeType.GUIDE,
                "指南: 液化池",
                "你可以使用液化池做出一些很酷的东西",
                "",
                "任何扔进液化池中的魔法水晶会转为液态",
                "当你扔进不是魔法水晶的物品时",
                "液化池会尝试使用该物品进行合成",
                "",
                "如果你丢入错误的物品，你会失去所有魔法水晶"
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Stave Configurator
        SlimefunItem guideStave = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_STAVE_CONFIGURATOR",
                new ItemStack(Material.CAULDRON),
                ThemeType.GUIDE,
                "指南: 法杖配置器",
                "将法杖放置在输入栏",
                "把充能的法术板放入法术栏",
                "点击'Add Plates'. Click 'Remove' to remove",
                "them again.",
                "",
                "Spells can be changed freely without cost."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Make a Spell
        SlimefunItem guideMakeSpell = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_MAKE_SPELL",
                new ItemStack(Material.PAPER),
                ThemeType.GUIDE,
                "指南: 制作法术",
                "To make a spell, first craft a Basic",
                "Spell Plate. The in the Liquefaction",
                "Basin, fill with at least 3 different",
                "types of Crystamae - then throw in the",
                "Plate"
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Recharge a Spell
        SlimefunItem guideChargeSpell = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_CHARGE_SPELL",
                new ItemStack(Material.PAPER),
                ThemeType.GUIDE,
                "指南: " +
                    "充能法术",
                "To refill a plate with crysta first",
                "take it off of the stave using the",
                "configurator. then in the Liquefaction",
                "Basin, make the spell recipe and throw",
                "the charged plate in. It will refill",
                "the Crysta inside."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Slimefun Registry
        ItemGroups.MAIN.register(plugin);
        //ItemGroups.STORY_COLLECTION.register(plugin);
        //ItemGroups.SPELL_COLLECTION.register(plugin);

        guideChronicler.register(plugin);
        guideRealisation.register(plugin);
        guideLiquefaction.register(plugin);
        guideStave.register(plugin);
        guideMakeSpell.register(plugin);
        guideChargeSpell.register(plugin);
    }
}

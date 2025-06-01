package io.github.sefiraat.crystamaehistoria;

import com.google.common.base.Preconditions;
import de.slikey.effectlib.EffectManager;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.commands.GetRanks;
import io.github.sefiraat.crystamaehistoria.commands.OpenSpellCompendium;
import io.github.sefiraat.crystamaehistoria.commands.OpenStoryCompendium;
import io.github.sefiraat.crystamaehistoria.commands.TestSpell;
import io.github.sefiraat.crystamaehistoria.commands.TestWand;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.managers.ConfigManager;
import io.github.sefiraat.crystamaehistoria.managers.ListenerManager;
import io.github.sefiraat.crystamaehistoria.managers.RunnableManager;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.managers.SupportedPluginManager;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.ArtisticItems;
import io.github.sefiraat.crystamaehistoria.slimefun.Exalted;
import io.github.sefiraat.crystamaehistoria.slimefun.Gadgets;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.Mechanisms;
import io.github.sefiraat.crystamaehistoria.slimefun.NetheoPlants;
import io.github.sefiraat.crystamaehistoria.slimefun.Runes;
import io.github.sefiraat.crystamaehistoria.slimefun.Tools;
import io.github.sefiraat.crystamaehistoria.slimefun.Uniques;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel.ChroniclerPanelCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.AdvancedPie;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CrystamaeHistoria extends AbstractAddon {

    private static CrystamaeHistoria instance;

    private ConfigManager configManager;
    private StoriesManager storiesManager;
    private ListenerManager listenerManager;
    private RunnableManager runnableManager;
    private SpellMemory spellMemory;
    private EffectManager effectManager;
    private SupportedPluginManager supportedPluginManager;

    public CrystamaeHistoria() {
        super("SlimefunGuguProject", "CrystamaeHistoria", "master", "auto-update");
    }

    public static CrystamaeHistoria getInstance() {
        return instance;
    }

    public static ConfigManager getConfigManager() {
        return instance.configManager;
    }

    public static StoriesManager getStoriesManager() {
        return instance.storiesManager;
    }

    public static ListenerManager getListenerManager() {
        return instance.listenerManager;
    }

    public static RunnableManager getRunnableManager() {
        return instance.runnableManager;
    }

    public static SpellMemory getSpellMemory() {
        return instance.spellMemory;
    }

    public static EffectManager getEffectManager() {
        return instance.effectManager;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return instance.supportedPluginManager;
    }

    public static PluginManager getPluginManager() {
        return instance.getServer().getPluginManager();
    }

    @Nonnull
    public static Map<MagicProjectile, Pair<CastInformation, Long>> getProjectileMap() {
        return instance.spellMemory.getProjectileMap();
    }

    @Nonnull
    public static Map<MagicFallingBlock, Pair<CastInformation, Long>> getFallingBlockMap() {
        return instance.spellMemory.getFallingBlockMap();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getStrikeMap() {
        return instance.spellMemory.getStrikeMap();
    }

    @Nonnull
    public static Map<MagicSummon, Long> getSummonedEntityMap() {
        return instance.spellMemory.getSummonedEntities();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getProjectileCastInfo(MagicProjectile magicProjectile) {
        CastInformation castInformation = getProjectileMap().get(magicProjectile).getFirstValue();
        Preconditions.checkNotNull(
            castInformation,
            "Cast information is null, magical projectile spawned incorrectly."
        );
        return castInformation;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getFallingBlockCastInfo(MagicFallingBlock magicFallingBlock) {
        CastInformation castInformation = getFallingBlockMap().get(magicFallingBlock).getFirstValue();
        Preconditions.checkNotNull(
            castInformation,
            "Cast information is null, magical falling block spawned incorrectly."
        );
        return castInformation;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getStrikeCastInfo(UUID lightningStrike) {
        CastInformation castInformation = getStrikeMap().get(lightningStrike).getFirstValue();
        Preconditions.checkNotNull(
            castInformation,
            "Cast information is null, magical projectile spawned incorrectly."
        );
        return castInformation;
    }

    @Override
    public void enable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("     CrystamaeHistoria  魔法水晶编年史    ");
        getLogger().info(" 作者: Sefiraat 汉化: SlimefunGuguProject");
        getLogger().info("########################################");

        if (PaperLib.isSpigot() && !PaperLib.isPaper()) {
            getLogger().warning("==========================================");
            getLogger().warning("魔法水晶编年史使用了部分 Paper 的特性");
            getLogger().warning("需要 Paper 以及衍生服务端才能运行");
            getLogger().warning("");
            getLogger().warning("Spigot 服务端无法运行魔法水晶编年史");
            getLogger().warning("请切换至 Paper 以及衍生服务端");
            getLogger().warning("==========================================");

            instance = null;
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getConfig().getBoolean("auto-update") && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "SlimefunGuguProject", "CrystamaeHistoria", "master");
        }

        this.configManager = new ConfigManager();
        this.storiesManager = new StoriesManager();
        this.listenerManager = new ListenerManager();
        this.runnableManager = new RunnableManager();
        this.spellMemory = new SpellMemory();
        this.supportedPluginManager = new SupportedPluginManager();
        this.effectManager = new EffectManager(this);

        configManager.loadConfig();

        SpellType.setupEnabledSpells();

        setupSlimefun();

        setupBstats();

        getAddonCommand().addSub(new TestSpell());
        getAddonCommand().addSub(new TestWand());
        getAddonCommand().addSub(new OpenSpellCompendium());
        getAddonCommand().addSub(new OpenStoryCompendium());
        getAddonCommand().addSub(new GetRanks());
    }

    private void setupBstats() {
        Metrics metrics = new Metrics(this, 12065);

        AdvancedPie disabledSpellsChart = new AdvancedPie("disabled_spells", () -> {
            Map<String, Integer> values = new HashMap<>();
            for (SpellType spellType : SpellType.getCachedValues()) {
                Spell spell = spellType.getSpell();
                values.put(spell.getId(), spell.isEnabled() ? 0 : 1);
            }
            return values;
        });

        AdvancedPie spellsCastChart = new AdvancedPie("spells_cast", () -> {
            Map<String, Integer> values = new HashMap<>();
            for (SpellType spellType : SpellType.getCachedValues()) {
                Spell spell = spellType.getSpell();
                int timesCast = 0;
                for (String string : CrystamaeHistoria.getConfigManager().getPlayerStats().getKeys(false)) {
                    UUID uuid = UUID.fromString(string);
                    timesCast += PlayerStatistics.getUsages(uuid, spellType);
                }
                values.put(spell.getId(), timesCast);
            }
            return values;
        });

        AdvancedPie storiesChronicled = new AdvancedPie("stories_chronicled", () -> {
            Map<String, Integer> values = new HashMap<>();
            for (BlockDefinition definition : CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().values()) {
                int timesChronicled = 0;
                for (String string : CrystamaeHistoria.getConfigManager().getPlayerStats().getKeys(false)) {
                    UUID uuid = UUID.fromString(string);
                    timesChronicled += PlayerStatistics.getChronicle(uuid, definition);
                }
                values.put(definition.getMaterial().toString(), timesChronicled);
            }
            return values;
        });

        metrics.addCustomChart(disabledSpellsChart);
        metrics.addCustomChart(spellsCastChart);
        metrics.addCustomChart(storiesChronicled);
    }

    @Override
    protected void disable() {
        if (instance != null) {
            for (ChroniclerPanelCache cache : ChroniclerPanel.getCaches().values()) {
                cache.shutdown();
            }

            spellMemory.clearAll();
            configManager.saveAll();
            instance = null;
        }
    }

    private void setupSlimefun() {
        ItemGroups.setup();
        Materials.setup();
        Mechanisms.setup();
        Tools.setup();
        Gadgets.setup();
        ArtisticItems.setup();
        Exalted.setup();
        Uniques.setup();
        Runes.setup();
        if (supportedPluginManager.isNetheopoiesis()){
            try {
                NetheoPlants.setup();
            } catch (NoClassDefFoundError e) {
                getLogger().severe("你必须更新下界乌托邦才能让魔法水晶编年史添加相关功能.");
            }
        }
    }
}

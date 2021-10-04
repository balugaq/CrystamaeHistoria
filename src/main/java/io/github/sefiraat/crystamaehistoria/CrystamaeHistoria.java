package io.github.sefiraat.crystamaehistoria;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.commands.TestSpell;
import io.github.sefiraat.crystamaehistoria.magic.ActiveStorage;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.managers.ListenerManager;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.managers.ThemeManager;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.apache.commons.lang.Validate;
import org.bstats.bukkit.Metrics;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.UUID;

public class CrystamaeHistoria extends AbstractAddon {

    private static CrystamaeHistoria instance;

    private KeyHolder keyHolder;
    private ThemeManager themeManager;
    private Structure structure;
    private ListenerManager listenerManager;
    private StoriesManager storiesManager;

    private ActiveStorage activeStorage;

    public CrystamaeHistoria() {
        super("Sefiraat", "CrystamaeHistoria", "master", "auto-update");
    }

    @Override
    public void enable() {

        instance = this;

        getLogger().info("########################################");
        getLogger().info("    Crystamae Historia - By Sefiraat    ");
        getLogger().info("########################################");

        this.keyHolder = new KeyHolder();
        this.themeManager = new ThemeManager();
        this.structure = new Structure();
        this.listenerManager = new ListenerManager();
        this.storiesManager = new StoriesManager();
        this.activeStorage = new ActiveStorage();

        new Metrics(this,12065);

        getAddonCommand().addSub(new TestSpell());

    }

    @Override
    protected void disable() {
        activeStorage.clearAll();
        saveConfig();
        instance = null;
    };

    public static CrystamaeHistoria inst() {
        return instance;
    }

    public static KeyHolder getKeyHolder() {
        return instance.keyHolder;
    }

    public static ThemeManager getThemeManager() {
        return instance.themeManager;
    }

    public static Structure getStructure() {
        return instance.structure;
    }

    public static ListenerManager getListenerManager() {
        return instance.listenerManager;
    }

    public static StoriesManager getStoriesManager() {
        return instance.storiesManager;
    }

    public static ActiveStorage getActiveStorage() {
        return instance.activeStorage;
    }

    public static PluginManager getPluginManager() {
        return instance.getServer().getPluginManager();
    }

    public static Server getServ() {
        return instance.getServer();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getProjectileMap() {
        return instance.activeStorage.getProjectileMap();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getSpellCastInfo(UUID uuid) {
        CastInformation castInformation = getProjectileMap().get(uuid).getFirstValue();
        Validate.notNull(castInformation, "Cast information is null, magical projectile spawned incorrectly.");
        return castInformation;
    }

    @Nonnull
    public static Map<SpellTick, Integer> getTickingMap() {
        return instance.activeStorage.getTickingCastables();
    }

    @ParametersAreNonnullByDefault
    // TODO Remove before v1
    public static void logInfo(String... message) {
        for (String string : message) {
            instance.getServer().getLogger().info(string);
        }
    }

    @ParametersAreNonnullByDefault
    // TODO Remove before v1
    public static void logWarning(String... message) {
        for (String string : message) {
            instance.getServer().getLogger().warning(string);
        }
    }

}

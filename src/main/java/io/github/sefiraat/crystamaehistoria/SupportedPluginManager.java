package io.github.sefiraat.crystamaehistoria;

import com.gmail.nossr50.util.skills.CombatUtils;
import io.github.thebusybiscuit.exoticgarden.items.BonemealableItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

@Getter
public class SupportedPluginManager {

    private final boolean mcMMO;
    private final boolean exoticGarden;
    private final boolean slimeTinker;

    public SupportedPluginManager() {
        mcMMO = Bukkit.getPluginManager().isPluginEnabled("mcMMO");
        exoticGarden = Bukkit.getPluginManager().isPluginEnabled("ExoticGarden");
        slimeTinker = Bukkit.getPluginManager().isPluginEnabled("SlimeTinker");
    }

    /**
     * Damaging an entity and attributing to a player will make mcMMO give exp based
     * on the held item. If mcMMO is installed, we need to flag the entity to be ignored
     * briefly.
     *
     * @param livingEntity The {@link LivingEntity} to be damaged
     * @param player       The {@link Player} to attribute the damage/drops to
     * @param damage       The damage to apply
     */
    public void playerDamageWithoutMcMMO(LivingEntity livingEntity, Player player, double damage) {
        markEntityMcMMOIgnoreDamage(livingEntity);
        livingEntity.damage(damage, player);
        clearEntityMcMMOIgnoreDamage(livingEntity);
    }

    public void markEntityMcMMOIgnoreDamage(LivingEntity livingEntity) {
        if (mcMMO) CombatUtils.applyIgnoreDamageMetadata(livingEntity);
    }

    public void clearEntityMcMMOIgnoreDamage(LivingEntity livingEntity) {
        if (mcMMO) CombatUtils.removeIgnoreDamageMetadata(livingEntity);
    }

    public boolean isExoticGardenPlant(Block block) {
        return exoticGarden
            && BlockStorage.hasBlockInfo(block)
            && BlockStorage.check(block) instanceof BonemealableItem;
    }

    /**
     * Gets the SlimefunItem for the ExoticPlant if it exists
     *
     * @param block The {@link Block} to check
     * @return Returns null if there is not a plant (or Exotic is not installed) or the
     * the SlimefunItem if applicable.
     */
    @Nullable
    public SlimefunItem getExoticGardenPlant(Block block) {
        if (exoticGarden && BlockStorage.hasBlockInfo(block)) {
            SlimefunItem slimefunItem = BlockStorage.check(block);
            if (slimefunItem instanceof BonemealableItem) {
                return slimefunItem;
            }
        }
        return null;
    }


}
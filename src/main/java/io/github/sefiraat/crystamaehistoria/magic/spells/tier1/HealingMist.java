package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class HealingMist extends Spell {

    public HealingMist() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100, true, 10, false, 5, true)
            .makeInstantSpell(this::cast)
            .makeEffectingSpell(true, false)
            .addPositiveEffect(PotionEffectType.REGENERATION, 1, 10);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range, Player.class::isInstance)) {
            Player player = (Player) entity;
            applyPositiveEffects(player, castInformation);
            ParticleUtils.displayParticleEffect(player, Particle.HEART, 2, 5);
            ParticleUtils.displayParticleEffect(player, Particle.CLOUD, 2, 2);
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.CELESTIAL,
            StoryType.VOID,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String getName() {
        return "恢复光环";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "在施法者周围释放治疗能量",
            "给自己附近玩家提供生命恢复"
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "HEALING_MIST";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GOLDEN_APPLE;
    }
}

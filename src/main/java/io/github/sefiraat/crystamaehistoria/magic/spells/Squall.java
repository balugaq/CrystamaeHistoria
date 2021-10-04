package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class Squall extends Spell {

    public Squall() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(2000, true, 0, false, 10, true)
                .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        if (caster != null) {
            caster.getWorld().setThundering(true);
            caster.getWorld().setStorm(true);
            displayParticleEffect(caster, Particle.ELECTRIC_SPARK, 2, 30);
            caster.getWorld().playEffect(caster.getLocation(), Effect.BONE_MEAL_USE, 1);
        }
    }
}

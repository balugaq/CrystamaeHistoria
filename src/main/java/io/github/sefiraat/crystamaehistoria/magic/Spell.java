package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.CallLightning;
import io.github.sefiraat.crystamaehistoria.magic.spells.FanOfArrows;
import io.github.sefiraat.crystamaehistoria.magic.spells.FireNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Fireball;
import io.github.sefiraat.crystamaehistoria.magic.spells.PoisonNova;
import io.github.sefiraat.crystamaehistoria.magic.spells.Quake;
import io.github.sefiraat.crystamaehistoria.magic.spells.RainOfFire;
import io.github.sefiraat.crystamaehistoria.magic.spells.Teleport;
import io.github.sefiraat.crystamaehistoria.magic.spells.Tempest;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.AbstractSpell;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum Spell {

    LIGHTNING_CALL("LIGHTNING_CALL", new CallLightning()),
    FAN_OF_ARROWS("FAN_OF_ARROWS", new FanOfArrows()),
    FIREBALL("FIREBALL", new Fireball()),
    POISON_NOVA("POISON_NOVA", new PoisonNova()),
    RAIN_OF_FIRE("RAIN_OF_FIRE", new RainOfFire()),
    TELEPORT("TELEPORT", new Teleport()),
    TEMPEST("TEMPEST", new Tempest()),
    FIRE_NOVA("FIRE_NOVA", new FireNova()),
    QUAKE("QUAKE", new Quake());

    private final String id;
    private final AbstractSpell spell;

    Spell(String id, AbstractSpell spell) {
        this.id = id;
        this.spell = spell;
    }

    @Nonnull
    public AbstractSpell get() {
        return spell;
    }

    public void cast(CastDefinition castDefinition) {
        this.spell.cast(castDefinition);
    }

    @Nullable
    public static AbstractSpell getById(String id) {
        for (Spell spell : values()) {
            if (spell.id.equals(id)) {
                return spell.spell;
            }
        }
        return null;
    }

}

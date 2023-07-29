package net.wizards.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.effect.RemoveOnHit;
import net.spell_engine.api.effect.Synchronized;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.SpellPower;
import net.wizards.WizardsMod;

public class Effects {
    public static StatusEffect frozen = new FrozenStatusEffect(StatusEffectCategory.HARMFUL, 0x99ccff)
            .setVulnerability(MagicSchool.FROST, new SpellPower.Vulnerability(0, 1F, 0F))
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    "052f3166-8ae7-11ed-a1eb-0242ac120002",
                    -1F,
                    EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public static StatusEffect frostShield = new FrostShieldStatusEffect(StatusEffectCategory.BENEFICIAL, 0x99ccff)
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    "0563d59a-8c60-11ed-a1eb-0242ac120002",
                    -0.5F,
                    EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public static void register() {
        RemoveOnHit.configure(frozen, true);
        Synchronized.configure(frozen, true);
        Synchronized.configure(frostShield, true);

        int rawId = 720;
        Registry.register(Registries.STATUS_EFFECT, rawId++, new Identifier(WizardsMod.ID, "frozen").toString(), frozen);
        Registry.register(Registries.STATUS_EFFECT, rawId++, new Identifier(WizardsMod.ID, "frost_shield").toString(), frostShield);
    }
}

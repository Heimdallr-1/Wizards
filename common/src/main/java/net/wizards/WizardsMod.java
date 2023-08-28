package net.wizards;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.loot.LootConfig;
import net.tinyconfig.ConfigManager;
import net.wizards.config.Default;
import net.wizards.config.EffectsConfig;
import net.wizards.config.WorldGenConfig;
import net.wizards.effect.Effects;
import net.wizards.item.Armors;
import net.wizards.item.Group;
import net.wizards.item.Weapons;
import net.wizards.item.WizardBooks;
import net.wizards.villager.WizardVillagers;

public class WizardsMod {
    public static final String ID = "wizards";

    public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
            ("items_v2", Default.itemConfig)
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .build();
    public static ConfigManager<LootConfig> lootConfig = new ConfigManager<LootConfig>
            ("loot_v2", Default.lootConfig)
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .constrain(LootConfig::constrainValues)
            .build();
    public static ConfigManager<WorldGenConfig> worldGenConfig = new ConfigManager<WorldGenConfig>
            ("world_gen", Default.worldGenConfig)
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .build();
    public static ConfigManager<EffectsConfig> effectsConfig = new ConfigManager<EffectsConfig>
            ("effects", new EffectsConfig())
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .build();

    public static void init() {
        lootConfig.refresh();
        itemConfig.refresh();
        effectsConfig.refresh();
        Registry.register(Registries.ITEM_GROUP, Group.KEY, Group.WIZARDS);
        WizardBooks.register();
        Weapons.register(itemConfig.value.weapons);
        Armors.register(itemConfig.value.armor_sets);
        itemConfig.save();
        worldGenConfig.refresh();
        Effects.register();
        WizardVillagers.register();
    }
}
package com.asteristired.weaponfusing.Item;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static com.asteristired.weaponfusing.Weaponfusing.MOD_ID;

public class ModItemTags {
    public static class Durability {
        public static final TagKey<Item> CONSUMABLE = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "durability/consumable"));
        public static final TagKey<Item> FRAGILE = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "durability/fragile"));
        public static final TagKey<Item> STRONG = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "durability/strong"));
        public static final TagKey<Item> VERY_STRONG = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "durability/very_strong"));
        public static void Initalise() {}
    }

    public static class Damage {
        public static final TagKey<Item> HIGH = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "damage/high"));
        public static final TagKey<Item> VERY_HIGH = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "damage/very_high"));
        public static final TagKey<Item> INSANELY_HIGH = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "damage/insanely_high"));
        public static void Initalise() {}
    }

    public static class Effects {
        public static final TagKey<Item> FLAMING = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "effects/flaming"));
        public static final TagKey<Item> FREEZING = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "effects/freezing"));
        public static final TagKey<Item> SHOCKING = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "effects/shocking"));
        public static final TagKey<Item> CAN_BE_EATEN = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "effects/can_be_eaten"));
        public static void Initalise() {}
    }

    public static void Initalise() {
        Durability.Initalise();
        Damage.Initalise();
        Effects.Initalise();
    }
}
package com.asteristired.weaponfusing.Item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.asteristired.weaponfusing.Weaponfusing.MOD_ID;

public class ModItemGroups {
    public static final ItemGroup FUSION = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.CRAFTING_TABLE)) // Icon for the tab
            .displayName(Text.translatable("itemGroup.weaponfusing.fusion"))
            .build();
    public static final RegistryKey<ItemGroup> FUSION_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "fusion"));

    public static void Initalise() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "fusion"), ModItemGroups.FUSION);
    }
}

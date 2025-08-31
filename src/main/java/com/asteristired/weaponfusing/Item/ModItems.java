package com.asteristired.weaponfusing.Item;

import com.asteristired.weaponfusing.Item.itemClasses.*;
import com.asteristired.weaponfusing.ItemList;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.asteristired.weaponfusing.Weaponfusing.MOD_ID;

public class ModItems {

    public static Item FUSED_SWORD = Register(new FuseItem(ToolMaterials.DIAMOND, 3, -2.4f,  new Item.Settings().rarity(Rarity.UNCOMMON), Items.DIAMOND_SWORD ), "fused_sword");

    public static Item FUSE_WOODEN_SWORD = Register(new WoodenSwordFuseItem(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
    ), "fuse_wooden_sword");
    public static Item FUSE_STONE_SWORD = Register(new StoneSwordFuseItem(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
    ), "fuse_stone_sword");
    public static Item FUSE_IRON_SWORD = Register(new IronSwordFuseItem(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
    ), "fuse_iron_sword");
    public static Item FUSE_GOLDEN_SWORD = Register(new GoldenSwordFuseItem(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
    ), "fuse_golden_sword");
    public static Item FUSE_DIAMOND_SWORD = Register(new DiamondSwordFuseItem(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
    ), "fuse_diamond_sword");
    public static Item FUSE_NETHERITE_SWORD = Register(new NetheriteSwordFuseItem(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
    ), "fuse_netherite_sword");

    public static Item Register(Item item, String id) {
        // create the identifier
        Identifier ID = new Identifier(MOD_ID, id);

        // register the item
        Item registeredItem = Registry.register(Registries.ITEM, ID, item);

        // return it
        return registeredItem;
    }

    public static void AddToGroups() {
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.FUSION_KEY).register(content -> {
            List<Item> modItemsList = Arrays.asList(ModItems.FUSE_WOODEN_SWORD, ModItems.FUSE_STONE_SWORD, ModItems.FUSE_IRON_SWORD, ModItems.FUSE_GOLDEN_SWORD, ModItems.FUSE_DIAMOND_SWORD, ModItems.FUSE_NETHERITE_SWORD);
            for (Item baseItem : modItemsList) {
                for (String itemId : ItemList.GetFuseList()) {
                    Item item = Registries.ITEM.get(new Identifier(itemId));
                    ItemStack stack = new ItemStack(baseItem);
                    NbtCompound nbt = new NbtCompound();
                    nbt.putString("FuseMaterial", itemId); // Example tag

                    stack.setNbt(nbt);

                    if (!Objects.equals(Registries.ITEM.getId(item).toString(), "minecraft:air")) {
                        content.add(stack);
                    }

                }
            }
        });
    }
    public static void Initalise() {
        return;
    }
}

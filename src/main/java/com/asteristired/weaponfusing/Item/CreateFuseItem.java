package com.asteristired.weaponfusing.Item;

import com.asteristired.weaponfusing.ItemList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

public class CreateFuseItem {

    public static ItemStack create(ItemStack base, ItemStack fused) {
        Item fuseBase = null;
        Item baseItem = base.getItem();

        // yes this is bad, no I do not care (well I do and I'll fix it at *some* point)
        // this should be a key value pair thing
        if (baseItem == Items.WOODEN_SWORD) fuseBase = ModItems.FUSE_WOODEN_SWORD;
        if (baseItem == Items.STONE_SWORD) fuseBase = ModItems.FUSE_STONE_SWORD;
        if (baseItem == Items.GOLDEN_SWORD) fuseBase = ModItems.FUSE_GOLDEN_SWORD;
        if (baseItem == Items.IRON_SWORD) fuseBase = ModItems.FUSE_IRON_SWORD;
        if (baseItem == Items.DIAMOND_SWORD) fuseBase = ModItems.FUSE_DIAMOND_SWORD;
        if (baseItem == Items.NETHERITE_SWORD) fuseBase = ModItems.FUSE_NETHERITE_SWORD;

        assert fuseBase != null;
        ItemStack fusedItem = fuseBase.getDefaultStack();

        fusedItem.setDamage(base.getDamage());
        fusedItem.getOrCreateNbt().putString("FuseMaterial", Registries.ITEM.getId(fused.getItem()).toString());
        fusedItem.getOrCreateNbt().putInt("CustomModelData", ItemList.GetCustomModelData(baseItem, fused.getItem()));

        return fusedItem;
    }
}

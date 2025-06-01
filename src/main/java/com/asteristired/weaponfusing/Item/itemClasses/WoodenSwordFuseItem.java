package com.asteristired.weaponfusing.Item.itemClasses;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

public class WoodenSwordFuseItem extends FuseItem {
    public WoodenSwordFuseItem(Settings settings) {
        super(ToolMaterials.WOOD, 3, -2.4f,  settings, Items.WOODEN_SWORD);
    }
}

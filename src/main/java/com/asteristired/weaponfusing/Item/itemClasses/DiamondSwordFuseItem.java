package com.asteristired.weaponfusing.Item.itemClasses;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;

public class DiamondSwordFuseItem extends FuseItem {
    public DiamondSwordFuseItem(Settings settings) {
        super(ToolMaterials.DIAMOND, 3, -2.4f,  settings, Items.DIAMOND_SWORD);
    }
}

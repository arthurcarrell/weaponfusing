package com.asteristired.weaponfusing.Item.itemClasses;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;

public class GoldenSwordFuseItem extends FuseItem {
    public GoldenSwordFuseItem(Settings settings) {
        super(ToolMaterials.GOLD, 3, -2.4f,  settings, Items.GOLDEN_SWORD);
    }
}

package com.asteristired.weaponfusing.Item.itemClasses;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;

public class StoneSwordFuseItem extends FuseItem {
    public StoneSwordFuseItem(Settings settings) {
        super(ToolMaterials.STONE, 4, -2.4f,  settings, Items.STONE_SWORD);
    }
}

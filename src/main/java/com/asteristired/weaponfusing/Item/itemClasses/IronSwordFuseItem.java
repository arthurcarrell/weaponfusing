package com.asteristired.weaponfusing.Item.itemClasses;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;

public class IronSwordFuseItem extends FuseItem {
    public IronSwordFuseItem(Settings settings) {
        super(ToolMaterials.IRON, 3, -2.4f,  settings, Items.IRON_SWORD);
    }
}

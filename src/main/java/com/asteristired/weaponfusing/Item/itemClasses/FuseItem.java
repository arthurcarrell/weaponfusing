package com.asteristired.weaponfusing.Item.itemClasses;

import com.asteristired.weaponfusing.ItemList;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

import static com.asteristired.weaponfusing.Weaponfusing.LOGGER;

public abstract class FuseItem extends SwordItem {
    protected Item baseItem;
    protected Item fuseMaterial;
    public FuseItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, Item baseItem) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.baseItem = baseItem;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        writeNBTData(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        writeNBTData(stack);
        tooltip.add(Text.literal("Fused with " + fuseMaterial.getTranslationKey()));
        assert stack.getNbt() != null;
        tooltip.add(Text.literal("(Debug) CustomModelData: " + stack.getNbt().getInt("CustomModelData")).formatted(Formatting.GRAY, Formatting.ITALIC));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public void writeNBTData(ItemStack itemStack) {
        LOGGER.info("writing NBT data");
        itemStack.getOrCreateNbt();

        assert itemStack.getNbt() != null;
        if (!itemStack.getNbt().contains("FuseMaterial")) {
            itemStack.getNbt().putString("FuseMaterial", "minecraft:air");
        }
        fuseMaterial = Registries.ITEM.get(new Identifier(itemStack.getNbt().getString("FuseMaterial")));

        itemStack.getNbt().putInt("CustomModelData", ItemList.GetCustomModelData(baseItem, fuseMaterial));
    }
    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        writeNBTData(itemStack);
        return itemStack;
    }
}

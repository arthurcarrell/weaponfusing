package com.asteristired.weaponfusing.client.FuseItem;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class FuseItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    @Override
    public void render(ItemStack itemStack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        System.out.println("Rendering FuseItem");
        // get the item renderer
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        // BASE ITEM =========

        ItemStack baseItem = new ItemStack(Items.DIAMOND_SWORD);
        switch (mode) {
            case GUI -> {
                matrices.translate(8 / 16f, 8 / 16f, 0f);
                matrices.scale(0.75f, 0.75f, 0.75f);
            }
            case FIRST_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND -> {
                matrices.translate(1.13, 5.2, 1.13);
                matrices.scale(1.02f, 1.02f, 0.68f);
            }
            case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                matrices.translate(0, 8.7, -0.3);
                matrices.scale(1.275f, 1.275f, 0.85f);
            }
            default -> {}
        }

        matrices.push();

        itemRenderer.renderItem(baseItem, mode, light, overlay, matrices, vertexConsumers, MinecraftClient.getInstance().world, 0);
        // FUSE ITEM =========
        ItemStack fuseItem = new ItemStack(Items.AMETHYST_SHARD);

        switch (mode) {
            case GUI -> {
                matrices.translate(8 / 16f, 8 / 16f, 0f);
                matrices.scale(0.8f, 0.8f, 0.8f);
            }
            case FIRST_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND -> {
                matrices.translate(1.13, 5.2, 1.13);
                matrices.scale(1.02f, 1.02f, 0.68f);
            }
            case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                matrices.translate(0, 8.7, -0.3);
                matrices.scale(1.275f, 1.275f, 0.85f);
            }
            default -> {}
        }

        matrices.push();

        itemRenderer.renderItem(fuseItem, mode, light, overlay, matrices, vertexConsumers, MinecraftClient.getInstance().world, 0);
    }


}
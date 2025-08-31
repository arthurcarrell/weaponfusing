package com.asteristired.weaponfusing.FuseProcess;

import com.asteristired.weaponfusing.Item.CreateFuseItem;
import com.asteristired.weaponfusing.ItemList;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PlayerFuse {

    public static void DoFuse(PlayerEntity player, ItemStack fuseItem) {
        ItemStack fusedSword = CreateFuseItem.create(player.getMainHandStack(), fuseItem);
        player.getInventory().setStack(player.getInventory().selectedSlot, fusedSword);
    }

    public static void DoItemCheck(PlayerEntity player, World world, boolean keybindPressed) {
        Vec3d eyePosition = player.getEyePos();
        Vec3d lookVector = player.getRotationVec(1.0f);

        // do raycast
        Box rayBox = player.getBoundingBox().stretch(lookVector.multiply(5)).expand(1.0);
        for (ItemEntity item : world.getEntitiesByClass(ItemEntity.class, rayBox, entity -> true)) {
            double dotProduct = item.getPos().subtract(eyePosition).normalize().dotProduct(lookVector);
            if (dotProduct >= 0.99f && player.isHolding(itemStack -> itemStack.isIn(ItemTags.SWORDS)) && ItemList.IsInList(item.getStack())) {
                if (keybindPressed) {
                    DoFuse(player, item.getStack());
                    item.kill();
                }
                item.setGlowing(true);

            } else {
                item.setGlowing(false);
            }
        }
    }

    public static void Initalise() {
        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
            for (PlayerEntity player : minecraftServer.getOverworld().getPlayers()) {
                if (!player.getWorld().isClient) {
                    DoItemCheck(player, player.getWorld(), false);
                }
            }
        });
    }
}

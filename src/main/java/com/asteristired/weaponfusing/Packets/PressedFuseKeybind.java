package com.asteristired.weaponfusing.Packets;

import com.asteristired.weaponfusing.FuseProcess.PlayerFuse;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

import static com.asteristired.weaponfusing.Weaponfusing.MOD_ID;

public class PressedFuseKeybind {
    public static final Identifier PRESSED_FUSE_KEYBIND = new Identifier(MOD_ID, "pressed_fuse_keybind");

    public static void Register() {
        ServerPlayNetworking.registerGlobalReceiver(PRESSED_FUSE_KEYBIND, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                PlayerFuse.DoItemCheck(player, player.getWorld(), true);
            });
        });
    }
}

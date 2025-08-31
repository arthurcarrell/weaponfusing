package com.asteristired.weaponfusing.client.Keybinds;

import com.asteristired.weaponfusing.Packets.PressedFuseKeybind;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

import static com.asteristired.weaponfusing.Weaponfusing.LOGGER;

public class FuseKeybind extends ModKeybind {
    public FuseKeybind(String translationKey, int code, String category) {
        super(translationKey, code, category);
    }

    @Override
    public void OnPressed() {
        LOGGER.info("key pressed");
        PacketByteBuf buf = PacketByteBufs.create();
        ClientPlayNetworking.send(PressedFuseKeybind.PRESSED_FUSE_KEYBIND, buf);
    }
}

package com.asteristired.weaponfusing.client.Keybinds;

import com.asteristired.weaponfusing.Packets.PressedFuseKeybind;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;

import static com.asteristired.weaponfusing.Weaponfusing.LOGGER;
public class ModKeybinds {
    private static KeyBinding fuseKB;

    public static void setupFuseKB() {
        fuseKB = KeyBindingHelper.registerKeyBinding(new FuseKeybind(
                "key.weaponfusing.fuse", // Translation Key
                InputUtil.GLFW_KEY_C, // Default key (C)
                "category.weaponfusing" // Category in controls menu
        ));
        LOGGER.info("Registered keybinding: {}", fuseKB.getTranslationKey());
    }

    public static void Initalise() {
        setupFuseKB();
    }

    public static void WatchKeybind(ModKeybind kb) {
        LOGGER.info("Watching new KB: {}", kb.getTranslationKey());
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            assert minecraftClient != null;

            if (kb.isPressed()) {
                kb.OnPressed();
            }
        });
    }
}

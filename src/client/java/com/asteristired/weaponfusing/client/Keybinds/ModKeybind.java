package com.asteristired.weaponfusing.client.Keybinds;

import net.minecraft.client.option.KeyBinding;

// CUSTOM Keybind system where all the keybind logic is handled in a class that is extended from
public abstract class ModKeybind extends KeyBinding {
    public ModKeybind(String translationKey, int code, String category) {
        super(translationKey, code, category);
        ModKeybinds.WatchKeybind(this);
    }

    public void OnPressed() {
        // on pressed stuff here
    }
}

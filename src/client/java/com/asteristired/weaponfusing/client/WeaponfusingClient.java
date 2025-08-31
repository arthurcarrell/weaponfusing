package com.asteristired.weaponfusing.client;

import com.asteristired.weaponfusing.Item.ModItems;
import com.asteristired.weaponfusing.client.FuseItem.FuseItemRenderer;
import com.asteristired.weaponfusing.client.Keybinds.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

public class WeaponfusingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BuiltinItemRendererRegistry.INSTANCE.register(
                ModItems.FUSED_SWORD,
                new FuseItemRenderer()
        );

        ModKeybinds.Initalise();
    }
}

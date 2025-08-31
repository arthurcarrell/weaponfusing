package com.asteristired.weaponfusing;

import com.asteristired.weaponfusing.DamageType.ModDamageTypes;
import com.asteristired.weaponfusing.FuseProcess.PlayerFuse;
import com.asteristired.weaponfusing.Item.ModItemGroups;
import com.asteristired.weaponfusing.Item.ModItemTags;
import com.asteristired.weaponfusing.Item.ModItems;
import com.asteristired.weaponfusing.Packets.PressedFuseKeybind;
import com.asteristired.weaponfusing.StatusEffect.ModStatusEffects;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Weaponfusing implements ModInitializer {

    public static final String MOD_ID = "weaponfusing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.Initalise();
        ModItemGroups.Initalise();
        ModItems.AddToGroups();
        ModItemTags.Initalise();
        ModStatusEffects.Initalise();
        ModDamageTypes.Initalise();
        PlayerFuse.Initalise();
        PressedFuseKeybind.Register();

    }
}

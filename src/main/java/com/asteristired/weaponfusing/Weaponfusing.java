package com.asteristired.weaponfusing;

import com.asteristired.weaponfusing.Item.ModItemGroups;
import com.asteristired.weaponfusing.Item.ModItems;
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

    }
}

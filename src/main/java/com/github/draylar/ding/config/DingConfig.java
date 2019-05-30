package com.github.draylar.ding.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1.annotation.ConfigEntry;
import net.minecraft.sound.SoundEvents;

@Config(name = "ding")
public class DingConfig implements ConfigData
{
    public String loadSound = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP.getId().getPath();
    public String alertSound = "entity.experience_orb.pickup";
    @ConfigEntry.Gui.Excluded
    public String[] triggerStrings = new String[0];
    public String textFormatName = "GOLD";
    public boolean highlightTriggers = false;
    public boolean playPingSound = false;
}


package com.github.draylar.ding;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import net.minecraft.sound.SoundEvents;

@Config(name = "ding")
public class DingConfig implements ConfigData
{
    public String soundEvent = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP.getId().getPath();

    public String[] triggerStrings = new String[0];
    public String textFormatName = "GOLD";
    public String pingSound = "entity.experience_orb.pickup";

    public boolean highlightTriggers = false;
    public boolean playPingSound = false;
}
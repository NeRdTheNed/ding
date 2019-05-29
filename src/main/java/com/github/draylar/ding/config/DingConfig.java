package com.github.draylar.ding.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import net.minecraft.sound.SoundEvents;

@Config(name = "ding")
public class DingConfig implements ConfigData
{
    public String soundEvent = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP.getId().getPath();
}

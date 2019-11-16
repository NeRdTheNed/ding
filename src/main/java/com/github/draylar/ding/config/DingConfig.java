package com.github.draylar.ding.config;

import com.github.draylar.ding.Ding;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import net.minecraft.sound.SoundEvents;

@Config(name = Ding.MODID)
public class DingConfig implements ConfigData {
    public String loadSound = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP.getId().getPath();
}


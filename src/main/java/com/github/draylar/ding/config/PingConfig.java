package com.github.draylar.ding.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;

@Config(name = "ping")
public
class PingConfig implements ConfigData
{
    public String[] triggerStrings = new String[0];
    public String textFormatName = "GOLD";
    public String pingSound = "entity.experience_orb.pickup";
    public boolean highlightTriggers = false;
    public boolean playPingSound = false;
}

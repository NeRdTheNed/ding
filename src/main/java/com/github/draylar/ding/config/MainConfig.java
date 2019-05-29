package com.github.draylar.ding.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1.annotation.ConfigEntry;

@Config(name = "main")
public class MainConfig implements ConfigData
{
    @ConfigEntry.Category("ding")
    public DingConfig dingConfig = new DingConfig();

    @ConfigEntry.Category("ping")
    public PingConfig pingConfig = new PingConfig();
}


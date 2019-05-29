package com.github.draylar.ding.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import net.minecraft.client.gui.Screen;

import java.util.Optional;
import java.util.function.Supplier;

public class ModMenuIntegration implements ModMenuApi
{
    @Override
    public String getModId()
    {
        return "ding";
    }

    @Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen)
    {
        return Optional.of(AutoConfig.getConfigScreen(MainConfig.class, screen));
    }
}

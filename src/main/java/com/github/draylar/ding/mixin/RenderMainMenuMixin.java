package com.github.draylar.ding.mixin;

import com.github.draylar.ding.Ding;
import com.github.draylar.ding.config.MainConfig;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import net.minecraft.client.gui.MainMenuScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenuScreen.class)
public abstract class RenderMainMenuMixin
{
    private static boolean firstTime = true;

    @Inject(at = @At("HEAD"), method = "render")
    public void render(int int_1, int int_2, float float_1, CallbackInfo ci)
    {
        if(firstTime)
        {
            MainConfig config = AutoConfig.getConfigHolder(MainConfig.class).getConfig();
            Identifier identifier = new Identifier(config.dingConfig.soundEvent);
            if(identifier.getNamespace() == "") identifier = new Identifier("minecraft", identifier.getPath());

            Ding.attemptPlayPing(identifier.toString());
            firstTime = false;
        }
    }
}

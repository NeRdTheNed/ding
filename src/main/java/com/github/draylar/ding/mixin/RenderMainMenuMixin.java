package com.github.draylar.ding.mixin;

import com.github.draylar.ding.Ding;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class RenderMainMenuMixin {

    private static boolean firstTime = true;

    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        if (firstTime) {
            Identifier loadSoundID = new Identifier(Ding.CONFIG.loadSound);

            if (loadSoundID.getNamespace().isEmpty()) {
                loadSoundID = new Identifier("minecraft", loadSoundID.getPath());
            }

            Ding.attemptPlayPing(loadSoundID);
            firstTime = false;
        }
    }
}

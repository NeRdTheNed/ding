package com.github.draylar.ding.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.audio.PositionedSoundInstance;
import net.minecraft.client.audio.SoundLoader;
import net.minecraft.client.gui.MainMenuScreen;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenuScreen.class)
public class DingMixin
{
	private boolean firstTime = true;

	@Inject(at = @At("HEAD"), method = "onInitialized()V")
	protected void onInitialized(CallbackInfo ci)
	{
		if(firstTime)
		{
			SoundLoader soundLoader = MinecraftClient.getInstance().getSoundLoader();
			soundLoader.play(PositionedSoundInstance.master(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
			firstTime = false;
		}
	}
}

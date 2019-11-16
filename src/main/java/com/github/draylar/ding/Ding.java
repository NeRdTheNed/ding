package com.github.draylar.ding;

import com.github.draylar.ding.config.DingConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ding implements ClientModInitializer {

	public static final String MODID = "modid";
	private static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final DingConfig CONFIG = AutoConfig.register(DingConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitializeClient() {

	}

	/**
	 * Attempts to play a sound to the player.
	 */
	public static void attemptPlayPing(Identifier soundID) {
		SoundEvent event = Registry.SOUND_EVENT.get(soundID);

		if (event != null) {
			SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
			soundManager.play(PositionedSoundInstance.master(event, 1.0f));
		} else {
			SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
			soundManager.play(PositionedSoundInstance.master(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
			LOGGER.warn("Sound event - " + soundID + " - is invalid! Falling back to default sound.");
		}
	}
}

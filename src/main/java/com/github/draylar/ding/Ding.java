package com.github.draylar.ding;

import com.github.draylar.ding.config.MainConfig;
import com.google.common.collect.Lists;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.audio.PositionedSoundInstance;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.util.List;

public class Ding implements ClientModInitializer
{
	public static MainConfig config;


	@Override
	public void onInitializeClient()
	{
		config = AutoConfig.register(MainConfig.class, GsonConfigSerializer::new).getConfig();
	}


	/**
	 * Attempts to play a sound to the player.
	 */
	public static void attemptPlayPing(String soundID)
	{
		SoundEvent event = Registry.SOUND_EVENT.get(new Identifier(soundID));

		if (event != null)
		{
			SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
			soundManager.play(PositionedSoundInstance.master(event, 1.0f));
		}

		// sound invalid, play default exp
		else
		{
			SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
			soundManager.play(PositionedSoundInstance.master(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
			System.out.println("[Ding] Sound event - " + config.pingSound + " - is invalid! Using default sound.");
		}
	}

	public static void highlightPhrase(TextComponent textComponent, String phrase)
	{
		if(config.highlightTriggers)
		{
			// make it easier to access these parts later
			String chatMessage = textComponent.getText();
			String playerTag = chatMessage.substring(0, chatMessage.indexOf(" "));
			String playerMessage = chatMessage.substring(chatMessage.indexOf(" "));

			// cast to a use-able sub-class
			TranslatableTextComponent translatableTextComponent = (TranslatableTextComponent) textComponent;

			try
			{
				// create list and add our message with formatted phrase to it
				List<TextComponent> list = Lists.newArrayList();

				// format might be null, check before adding
				TextFormat format = TextFormat.getFormatByName(config.textFormatName) == null ? TextFormat.YELLOW : TextFormat.getFormatByName(config.textFormatName);

				// create message: player tag, beginning of message, colored phrase, end of message.
				list.add(new StringTextComponent(playerTag));
				list.add(new StringTextComponent(playerMessage.substring(0, playerMessage.indexOf(phrase))));
				list.add(new StringTextComponent(phrase).applyFormat(format));
				list.add(new StringTextComponent(playerMessage.substring(playerMessage.indexOf(phrase) + phrase.length())));

				// change the text message to have our modified message
				Field myField = translatableTextComponent.getClass().getDeclaredField("translatedText");
				myField.setAccessible(true);
				myField.set(translatableTextComponent, list);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}

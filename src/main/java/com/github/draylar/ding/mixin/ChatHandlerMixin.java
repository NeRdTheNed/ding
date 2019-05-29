package com.github.draylar.ding.mixin;

import com.github.draylar.ding.Ding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatListenerHud;
import net.minecraft.text.ChatMessageType;
import net.minecraft.text.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatListenerHud.class)
public class ChatHandlerMixin
{
    @Inject(at = @At("HEAD"), method = "onChatMessage")
    private void onChat(ChatMessageType chatMessageType_1, TextComponent textComponent, CallbackInfo ci)
    {
        String currentUsername = MinecraftClient.getInstance().player.getName().getText();
        String chatMessage = textComponent.getText();
        String playerMessage = chatMessage.substring(chatMessage.indexOf(" "));

        // check for username, format for message is:
        // <PlayerUsername> Hello, world!
        // we  have to replace the <PlayerUsername> start to prevent it from reporting.
        if(playerMessage.contains(currentUsername))
        {
            if(Ding.config.playPingSound)
            {
                Ding.attemptPlayPing(Ding.config.pingSound);
            }

            Ding.highlightPhrase(textComponent, currentUsername);
        }

        // username is not in text, check for word list
        else
            for (String triggerString : Ding.config.triggerStrings)
                if (playerMessage.contains(triggerString))
                {
                    if(Ding.config.playPingSound)
                    {
                        Ding.attemptPlayPing(Ding.config.pingSound);
                    }

                    Ding.highlightPhrase(textComponent, triggerString);
                    break;
                }
    }
}

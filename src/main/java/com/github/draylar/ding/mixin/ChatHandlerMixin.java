package com.github.draylar.ding.mixin;

import com.github.draylar.ding.Ding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatListenerHud;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatListenerHud.class)
public class ChatHandlerMixin
{
    @Inject(at = @At("HEAD"), method = "onChatMessage")
    private void onChat(ChatMessageType chatMessageType_1, Component component_1, CallbackInfo ci)
    {
        String currentUsername = MinecraftClient.getInstance().player.getName().getText();
        String chatMessage = component_1.getText();
        int index = chatMessage.indexOf(" ");

        String playerMessage;

        // simple check to remove player username
        if (index != -1 && chatMessage.contains("<") && chatMessage.contains(">"))
        {
            playerMessage = chatMessage.substring(chatMessage.indexOf(" "));
        }

        else
        {
            playerMessage = chatMessage;
        }

        // check for username, format for message is:
        // <PlayerUsername> Hello, world!
        // we  have to replace the <PlayerUsername> start to prevent it from reporting.
        if (playerMessage.contains(currentUsername))
        {
            if (Ding.config.playPingSound)
            {
                Ding.attemptPlayPing(Ding.config.alertSound);
            }

            Ding.highlightPhrase(component_1, currentUsername);
        }

        // username is not in text, check for word list
        else
            for (String triggerString : Ding.config.triggerStrings)
                if (playerMessage.contains(triggerString))
                {
                    if (Ding.config.playPingSound)
                    {
                        Ding.attemptPlayPing(Ding.config.alertSound);
                    }

                    Ding.highlightPhrase(component_1, triggerString);
                    break;
                }

    }
}

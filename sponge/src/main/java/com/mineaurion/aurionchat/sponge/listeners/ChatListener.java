package com.mineaurion.aurionchat.sponge.listeners;

import com.mineaurion.aurionchat.common.ChatService;
import com.mineaurion.aurionchat.common.Utils;
import com.mineaurion.aurionchat.sponge.AurionChat;
import com.mineaurion.aurionchat.sponge.AurionChatPlayer;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.IsCancelled;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.PlayerChatEvent;
import org.spongepowered.api.util.Tristate;

public class ChatListener {

    private final AurionChat plugin;

    public ChatListener(AurionChat plugin){
        this.plugin = plugin;
    }

    @Listener @IsCancelled(Tristate.UNDEFINED)
    public void onPlayerChat(PlayerChatEvent event, @First ServerPlayer player){
        if(event.isCancelled()) {
            return;
        }
        if(!player.hasPermission("aurionchat.chat.speak")){
            event.setCancelled(true);
            return;
        }
        AurionChatPlayer aurionChatPlayer = this.plugin.getAurionChatPlayers().get(player.uniqueId());

        String currentChannel = aurionChatPlayer.getCurrentChannel();
        Component messageFormat = Utils.processMessage(
                AurionChat.config.channels.get(currentChannel).format,
                event.message(),
                aurionChatPlayer
        );

        try{
            ChatService.getInstance().send(currentChannel, messageFormat);
        }
        catch (Exception e){
           this.plugin.getlogger().severe(e.getMessage());
        }
        event.setCancelled(true);  // TODO: need to remove that. Need to adapt rabbitmq to a fanout exchange for the chat.
    }
}

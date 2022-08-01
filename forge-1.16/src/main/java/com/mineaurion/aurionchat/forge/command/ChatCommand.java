package com.mineaurion.aurionchat.forge.command;

import com.mineaurion.aurionchat.common.command.ChatCommandCommon;
import com.mineaurion.aurionchat.forge.AurionChat;
import com.mineaurion.aurionchat.forge.AurionChatPlayer;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ChatCommand extends ChatCommandCommon<AurionChatPlayer> {
    public ChatCommand(CommandDispatcher<CommandSource> dispatcher){
        super(AurionChat.config.getChannels().keySet());
        this.register(dispatcher);
    }
    public void register(CommandDispatcher<CommandSource> dispatcher)
    {
        RequiredArgumentBuilder<CommandSource, String> channelArg = Commands.argument("channel", StringArgumentType.string()).suggests(((context, builder) -> {
            AurionChat.config.getChannels().forEach((name, channel) -> builder.suggest(name));
            return builder.buildFuture();
        }));

        LiteralCommandNode<CommandSource> join = Commands.literal("join")
                .then(channelArg.executes(ctx -> this.execute(ctx, Action.JOIN)))
                .build();

        LiteralCommandNode<CommandSource> leave = Commands.literal("leave")
                .then(channelArg.executes(ctx -> this.execute(ctx, Action.LEAVE)))
                .build();

        LiteralCommandNode<CommandSource> spy = Commands.literal("spy")
                .then(channelArg.executes(ctx -> this.execute(ctx, Action.SPY)))
                .build();

        LiteralCommandNode<CommandSource> allListen = Commands.literal("allListen")
                .executes(ctx -> this.execute(ctx, Action.ALLLISTEN))
                .build();


        dispatcher.register(
                Commands.literal("channel")
                        .then(join)
                        .then(leave)
                        .then(spy)
                        .then(allListen)
                        .executes(ctx -> this.execute(ctx, Action.DEFAULT))
        );
    }

    private int execute(CommandContext<CommandSource> ctx, Action action) {
        try {
            ServerPlayerEntity player = ctx.getSource().getPlayerOrException();
            AurionChatPlayer aurionChatPlayer = AurionChat.aurionChatPlayers.get(player.getUUID());
            String channel;
            try{
                channel = StringArgumentType.getString(ctx, "channel");
            } catch (IllegalArgumentException e){
                channel = "";
            }
            return this.execute(aurionChatPlayer, channel , action) ? 1 : 0;
        } catch (CommandSyntaxException e){
            ctx.getSource().sendFailure(new StringTextComponent("You need to be a player to do this"));
            return 0;
        }
    }
}
package com.mineaurion.aurionchat.common.message;

import com.mineaurion.aurionchat.api.AurionPacket;
import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public abstract class MessageProcessor<MPC extends MessageProcessorConfig> implements BiConsumer<Message, AurionPacket.Builder> {
    protected final MPC config;

    protected MessageProcessor(MPC config) {
        this.config = config;
    }

    public MPC getConfig() {
        return config;
    }
}

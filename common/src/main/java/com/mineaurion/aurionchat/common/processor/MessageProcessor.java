package com.mineaurion.aurionchat.common.processor;

import com.mineaurion.aurionchat.api.AurionPacket;
import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;
import com.mineaurion.aurionchat.api.model.Message;

import java.util.function.BiConsumer;

public abstract class MessageProcessor<MPC extends MessageProcessorConfig> implements BiConsumer<Message, AurionPacket.Builder> {
    protected final MPC config;

    protected MessageProcessor(MPC config) {
        this.config = config;
    }

    public MPC getConfig() {
        return config;
    }
}

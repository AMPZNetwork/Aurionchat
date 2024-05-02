package com.mineaurion.aurionchat.common.message;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;

import java.util.function.UnaryOperator;

public abstract class MessageProcessor<MPC extends MessageProcessorConfig> implements UnaryOperator<Message> {
    private final MPC config;

    protected MessageProcessor(MPC config) {
        this.config = config;
    }

    public MPC getConfig() {
        return config;
    }
}

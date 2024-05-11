package com.mineaurion.aurionchat.common.message.impl;

import com.mineaurion.aurionchat.api.AurionPacket;
import com.mineaurion.aurionchat.common.config.impl.UrlConverterConfig;
import com.mineaurion.aurionchat.common.message.Message;
import com.mineaurion.aurionchat.common.message.MessageProcessor;

public class UrlMessageProcessor extends MessageProcessor<UrlConverterConfig> {
    public UrlMessageProcessor(UrlConverterConfig config) {
        super(config);
    }

    @Override
    public void accept(Message message, AurionPacket.Builder packet) {
    }
}

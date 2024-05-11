package com.mineaurion.aurionchat.common.processor;

import com.mineaurion.aurionchat.api.AurionPacket;
import com.mineaurion.aurionchat.common.config.impl.UrlConverterConfig;
import com.mineaurion.aurionchat.api.model.Message;

public class UrlMessageProcessor extends MessageProcessor<UrlConverterConfig> {
    public UrlMessageProcessor(UrlConverterConfig config) {
        super(config);
    }

    @Override
    public void accept(Message message, AurionPacket.Builder packet) {
    }
}

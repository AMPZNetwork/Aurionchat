package com.mineaurion.aurionchat.common.message.impl;

import com.mineaurion.aurionchat.api.AurionPacket;
import com.mineaurion.aurionchat.common.config.impl.MarkdownConverterConfig;
import com.mineaurion.aurionchat.common.message.Message;
import com.mineaurion.aurionchat.common.message.MessageProcessor;

public class MarkdownMessageProcessor extends MessageProcessor<MarkdownConverterConfig> {
    public MarkdownMessageProcessor(MarkdownConverterConfig config) {
        super(config);
    }

    @Override
    public void accept(Message message, AurionPacket.Builder packet) {
        // todo https://github.com/Mineaurion/Aurionchat/pull/49#issuecomment-2081126731
    }
}

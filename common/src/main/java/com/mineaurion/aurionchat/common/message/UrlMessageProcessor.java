package com.mineaurion.aurionchat.common.message;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;

public class UrlMessageProcessor extends MessageProcessor<MessageProcessorConfig.UrlConverter> {
    public UrlMessageProcessor(MessageProcessorConfig.UrlConverter config) {
        super(config);
    }

    @Override
    public Message apply(Message message) {
    }
}

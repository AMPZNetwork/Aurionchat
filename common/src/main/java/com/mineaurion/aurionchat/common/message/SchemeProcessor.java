package com.mineaurion.aurionchat.common.message;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;

public class SchemeProcessor extends MessageProcessor<MessageProcessorConfig.SchemeConverter> {
    public SchemeProcessor(MessageProcessorConfig.SchemeConverter config) {
        super(config);
    }

    @Override
    public Message apply(Message message) {
        return message;
    }
}

package com.mineaurion.aurionchat.common.message;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;

public class MarkdownMessageProcessor extends MessageProcessor<MessageProcessorConfig.MarkdownConverter> {
    public MarkdownMessageProcessor(MessageProcessorConfig.MarkdownConverter config) {
        super(config);
    }

    @Override
    public Message apply(Message message) {
        return message; // todo https://github.com/Mineaurion/Aurionchat/pull/49#issuecomment-2081126731
    }
}

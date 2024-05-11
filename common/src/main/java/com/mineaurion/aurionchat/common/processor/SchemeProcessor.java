package com.mineaurion.aurionchat.common.processor;

import com.mineaurion.aurionchat.api.AurionPacket;
import com.mineaurion.aurionchat.common.config.impl.SchemeConverterConfig;
import com.mineaurion.aurionchat.api.model.Message;

public class SchemeProcessor extends MessageProcessor<SchemeConverterConfig> {
    public SchemeProcessor(SchemeConverterConfig config) {
        super(config);
    }

    @Override
    public void accept(Message message, AurionPacket.Builder packet) {
        if (!config.isEnabled())
            return;
    }
}

package com.mineaurion.aurionchat.common.config.impl;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;
import com.mineaurion.aurionchat.common.message.impl.SchemeProcessor;

public final class SchemeConverterConfig extends MessageProcessorConfig {
    public static final String KEY = "scheme";

    static {
        REGISTRY.put(KEY, new Factory<>(SchemeConverterConfig.class,SchemeConverterConfig::new));
    }

    private String scheme;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public SchemeProcessor createMessageProcessor() {
        return new SchemeProcessor(this);
    }
}

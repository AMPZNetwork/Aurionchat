package com.mineaurion.aurionchat.common.config.impl;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;
import com.mineaurion.aurionchat.common.message.impl.UrlMessageProcessor;

public final class UrlConverterConfig extends MessageProcessorConfig {
    public static final String KEY = "urls";

    static {
        REGISTRY.put(KEY, new Factory<>(UrlConverterConfig.class,UrlConverterConfig::new));
    }

    private boolean requireProtocol;
    private boolean printDomainOnly;
    private boolean forceHttps;
    private boolean redact;

    public boolean isRequireProtocol() {
        return requireProtocol;
    }

    public void setRequireProtocol(boolean requireProtocol) {
        this.requireProtocol = requireProtocol;
    }

    public boolean isPrintDomainOnly() {
        return printDomainOnly;
    }

    public void setPrintDomainOnly(boolean printDomainOnly) {
        this.printDomainOnly = printDomainOnly;
    }

    public boolean isForceHttps() {
        return forceHttps;
    }

    public void setForceHttps(boolean forceHttps) {
        this.forceHttps = forceHttps;
    }

    public boolean isRedact() {
        return redact;
    }

    public void setRedact(boolean redact) {
        this.redact = redact;
    }

    @Override
    public UrlMessageProcessor createMessageProcessor() {
        return new UrlMessageProcessor(this);
    }
}

package com.mineaurion.aurionchat.common.config.impl;

import com.mineaurion.aurionchat.common.config.MessageProcessorConfig;
import com.mineaurion.aurionchat.common.processor.MarkdownMessageProcessor;

public final class MarkdownConverterConfig extends MessageProcessorConfig {
    public static final String KEY = "markdown";

    static {
        REGISTRY.put(KEY, new Factory<>(MarkdownConverterConfig.class,MarkdownConverterConfig::new));
    }

    private boolean obfuscated = true;
    private boolean bold = true;
    private boolean strikethrough = true;
    private boolean underlined = true;
    private boolean italic = true;

    public boolean isObfuscated() {
        return obfuscated;
    }

    public void setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    @Override
    public MarkdownMessageProcessor createMessageProcessor() {
        return new MarkdownMessageProcessor(this);
    }
}

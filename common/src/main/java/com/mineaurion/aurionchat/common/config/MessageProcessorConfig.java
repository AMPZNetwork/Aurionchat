package com.mineaurion.aurionchat.common.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class MessageProcessorConfig {
    public static final Map<String, MessageProcessorConfig.Factory<?>> REGISTRY = new ConcurrentHashMap<>();
    private boolean enable = true;

    public static final class Factory<T extends MessageProcessorConfig> {
        private final Class<T> type;
        private final Supplier<T> ctor;

        public Factory(Class<T> type, Supplier<T> ctor) {
            this.type = type;
            this.ctor = ctor;
        }

        public Class<T> getType() {
            return type;
        }

        public Supplier<T> getCtor() {
            return ctor;
        }

        public T create() {
            return ctor.get();
        }
    }

    public static final class MarkdownConverter extends MessageProcessorConfig {
        public static final String KEY = "markdown";

        static {
            REGISTRY.put(KEY, new Factory<>(MarkdownConverter.class, MarkdownConverter::new));
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
    }

    public static final class UrlConverter extends MessageProcessorConfig {
        public static final String KEY = "urls";

        static {
            REGISTRY.put(KEY, new Factory<>(UrlConverter.class, UrlConverter::new));
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
    }

    public static final class SchemeConverter extends MessageProcessorConfig {
        public static final String KEY = "scheme";

        static {
            REGISTRY.put(KEY, new Factory<>(SchemeConverter.class, SchemeConverter::new));
        }

        private String scheme;

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }
    }
}

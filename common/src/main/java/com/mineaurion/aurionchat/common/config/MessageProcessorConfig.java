package com.mineaurion.aurionchat.common.config;

import com.mineaurion.aurionchat.common.processor.MessageProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class MessageProcessorConfig {
    public static final Map<String, MessageProcessorConfig.Factory<?>> REGISTRY = new ConcurrentHashMap<>();
    private boolean enabled = true;

    public abstract MessageProcessor<?> createMessageProcessor();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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
}

package com.mineaurion.aurionchat.common.config;

import com.mineaurion.aurionchat.common.message.MessageProcessor;

import java.util.List;

public class Channel {
    private final String name;
    private final String alias;
    private final List<MessageProcessor<?>> formatting;

    public Channel(String name, String alias, List<MessageProcessor<?>> formatting) {
        this.name = name;
        this.alias = alias;
        this.formatting = formatting;
    }
}

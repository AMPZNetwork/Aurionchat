package com.mineaurion.aurionchat.api.model;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Message {
    public static final String FT_UNPROCESSED = "unprocessed";
    public static final String FT_USERNAME = "username";
    public static final String FT_DISPLAYNAME = "displayname";
    public static final String FT_URL = "url";
    public static final String FT_MARKDOWN = "markdown";
    public static final String FT_MESSAGE = "message";
    public final List<Component> components = new ArrayList<>();

    public Stream<Component> features(@MagicConstant(valuesFromClass = Message.class) String type) {
        return components.stream().filter(component -> component.features.contains(type));
    }

    public String getDisplayString() {
        return components.stream()
                .map(Component::getContent)
                .collect(Collectors.joining());
    }

    public TextComponent kyori() {
        TextComponent.Builder comp = net.kyori.adventure.text.Component.text();
        for (Component component : components) {
            TextComponent txt = net.kyori.adventure.text.Component.text(component.content)
                    .color(component.color);
            if (component.decoration != null)
                txt = txt.decoration(component.decoration, true);
            if (component.url != null)
                txt = txt.clickEvent(ClickEvent.openUrl(component.url));
            comp.append(txt);
        }
        return comp.build();
    }

    public static final class Component implements Cloneable {
        public final Set<String> features = new HashSet<>();
        public final Message message;
        private String content;
        private @Nullable TextColor color;
        private @Nullable TextDecoration decoration;
        private @Nullable String url;

        public Component(Message message) {
            this.message = message;
        }

        @Override
        @SuppressWarnings("MethodDoesntCallSuperMethod") // not necessary here
        public Component clone() {
            Component component = new Component(message)
                    .setContent(content)
                    .setColor(color)
                    .setDecoration(decoration)
                    .setUrl(url);
            component.features.addAll(features);
            return component;
        }

        public Component[] splitLeft(int index, BiFunction<Component,String,Component> left) {
            return split(index, left, Component::setContent);
        }

        public Component[] splitRight(int index, BiFunction<Component,String,Component> right) {
            return split(index, Component::setContent, right);
        }

        public Component[] split(int index, BiFunction<Component,String,Component> left, BiFunction<Component,String,Component> right) {
            String lStr = content.substring(0, index), rStr = content.substring(index);
            Component lComp = left.apply(clone(), lStr), rComp = right.apply(clone(), rStr);
            if (Objects.equals(lComp, rComp))
                throw new AssertionError("suspicious call to split(): left and right components should not be equal");
            index = message.components.indexOf(this);
            message.components.set(index, lComp);
            message.components.add(index + 1, rComp);
            return new Component[]{lComp, rComp};
        }

        public Set<String> getFeatures() {
            return features;
        }

        public String getContent() {
            return content;
        }

        public Component setContent(String content) {
            this.content = content;
            return this;
        }

        public TextColor getColor() {
            return color;
        }

        public Component setColor(TextColor color) {
            this.color = color;
            return this;
        }

        public TextDecoration getDecoration() {
            return decoration;
        }

        public Component setDecoration(TextDecoration decoration) {
            this.decoration = decoration;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Component setUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public String toString() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Component component = (Component) o;
            return Objects.equals(content, component.content)
                    && Objects.equals(color, component.color)
                    && decoration == component.decoration
                    && Objects.equals(url, component.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content, color, decoration, url);
        }
    }
}

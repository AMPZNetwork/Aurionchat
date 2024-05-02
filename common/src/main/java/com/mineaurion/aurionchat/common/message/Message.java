package com.mineaurion.aurionchat.common.message;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.*;
import java.util.function.BiFunction;

public final class Message {
    public final List<Component> components = new ArrayList<>();

    public static final class Component implements Cloneable {
        public final Set<String> features = new HashSet<>();
        public final Message message;
        private String content;
        private TextColor color;
        private TextDecoration decoration;
        private String url;

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

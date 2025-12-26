package org.example.demo.realization.level.transport;

@FunctionalInterface
public interface Channel {

    void addToChannel(ApplicationEvent event);
}

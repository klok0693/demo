package org.example.astero_demo.realization.level.transport;

@FunctionalInterface
public interface Channel {

    void addToChannel(ApplicationEvent event);
}

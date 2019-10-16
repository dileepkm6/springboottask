package com.stackroute.listenerhandler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStopListener implements ApplicationListener<ContextStoppedEvent>
{

    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("Application Context Stopped"+contextStoppedEvent.getSource());
    }
}

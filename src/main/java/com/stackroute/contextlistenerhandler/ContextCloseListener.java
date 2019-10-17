package com.stackroute.contextlistenerhandler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextCloseListener implements ApplicationListener<ContextClosedEvent>
{

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("Application Context Closed"+contextClosedEvent.getSource());

    }
}

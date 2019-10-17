package com.stackroute.contextlistenerhandler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStartListener implements ApplicationListener<ContextStartedEvent>
{
    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("Application Context Started"+contextStartedEvent.getSource());
    }
}

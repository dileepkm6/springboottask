package com.stackroute.contextlistenerhandler;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent>
{
    private TrackService trackService;
    @Autowired
    public ContextRefreshListener(@Qualifier("trackService") TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Application Context Refreshed"+contextRefreshedEvent.getSource());
        Track track=new Track();
        track.setTrackName("ApplicationListener");
        track.setComments("filled by ContextRefreshedEvent");
        try {
            trackService.saveTrack(track);
        } catch (TrackAlreadyExistException e) {
            e.getMessage();
        }
    }
}

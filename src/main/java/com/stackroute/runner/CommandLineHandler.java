package com.stackroute.runner;

import com.stackroute.domain.Track;
import com.stackroute.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineHandler implements CommandLineRunner
{
    @Autowired
    TrackService trackService;
    @Override
    public void run(String... args) throws Exception
    {
        Track track=new Track();
        track.setTrackName("CommandLineRunner");
        track.setComments("filled by CommandLineRunner");
        //filling data with CommandLineRunner
        trackService.saveTrack(track);

    }
}

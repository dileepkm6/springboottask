package com.stackroute.runner;

import com.stackroute.domain.Track;
import com.stackroute.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CommandLineHandler implements CommandLineRunner
{
    @Autowired
    TrackService trackService;
    @Autowired
    private Environment env;
    @Override
    public void run(String... args) throws Exception
    {
        Track track=new Track();
        track.setTrackName(env.getProperty("Track.trackName"));
        track.setComments(env.getProperty("Track.trackComment"));
        //filling data with CommandLineRunner
        trackService.saveTrack(track);

    }
}

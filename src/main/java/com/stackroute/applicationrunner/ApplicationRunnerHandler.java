package com.stackroute.applicationrunner;

import com.stackroute.domain.Track;
import com.stackroute.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerHandler implements ApplicationRunner
{
    private TrackService trackService;
    @Autowired
    public ApplicationRunnerHandler(@Qualifier("trackService") TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        Track track=new Track();
        track.setTrackName("applicationrunner");
        track.setComments("filled by application runner");
        //filling data with CommandLineRunner
        trackService.saveTrack(track);
    }
}

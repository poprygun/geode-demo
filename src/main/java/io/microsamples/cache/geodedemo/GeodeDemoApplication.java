package io.microsamples.cache.geodedemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class GeodeDemoApplication {

    @Autowired
    private Region longTrackRegion;


    public static void main(String[] args) {
        SpringApplication.run(GeodeDemoApplication.class, args);
    }

    @Bean
    @SuppressWarnings("unused")
    ApplicationRunner runner(TrackRepository trackRepository) {

        return args -> {

            Track track = new Track().setId(1L).setName("B-2");

            log.debug("Saving track -----> {}", track);

            track = trackRepository.save(track);

            Track foundTrack = trackRepository.findByNameLike("%B-2");

            log.debug("Found track -----> {}", foundTrack);
        };
    }
}

package io.microsamples.cache.geodedemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;



@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = Track.class
		, clientRegionShortcut = ClientRegionShortcut.CACHING_PROXY)
@EnableGemfireRepositories
@EnablePdx
@Slf4j
public class GeodeDemoApplication {

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

			log.info("Querying for Customer [SELECT * FROM /Tracks WHERE name LIKE '%Doe']...");

			Track foundTrack = trackRepository.findByNameLike("%B-2");

			log.debug("Found track -----> {}", foundTrack);
		};
	}
}

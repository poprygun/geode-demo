package io.microsamples.cache.geodedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.client.PoolFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.ConnectionEndpoint;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ClientCacheApplication(name = "TrackCacheApp")
@EnableGemfireRepositories(basePackageClasses = TrackRepository.class)
@EnableEntityDefinedRegions(basePackageClasses = Track.class)
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

			log.info("Saving Customer [%s]...%n", track);

			track = trackRepository.save(track);

			log.info("Querying for Customer [SELECT * FROM /Tracks WHERE name LIKE '%Doe']...");

			Track foundTrack = trackRepository.findByNameLike("%B-2");

			log.info("Track was [%s]%n", foundTrack);
		};
	}

	@Bean
	PoolFactoryBean gemfirePool(
			@Value("${gemfire.cache.server.host:localhost}") String host,
			@Value("${gemfire.cache.server.port:40404}") int port) {

		PoolFactoryBean gemfirePool = new PoolFactoryBean();

		gemfirePool.setKeepAlive(false);
		gemfirePool.setPingInterval(TimeUnit.SECONDS.toMillis(5));
		gemfirePool.setReadTimeout(Long.valueOf(TimeUnit.SECONDS.toMillis(15)).intValue());
		gemfirePool.setRetryAttempts(1);
		gemfirePool.setSubscriptionEnabled(true);
		gemfirePool.setThreadLocalConnections(false);
		gemfirePool.setServers(Collections.singleton(newConnectionEndpoint(host, port)));

		return gemfirePool;
	}

	ConnectionEndpoint newConnectionEndpoint(String host, int port) {
		return new ConnectionEndpoint(host, port);
	}
}

package io.microsamples.cache.geodedemo;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@ClientCacheApplication(name = "AccessingDataGemFireApplication",
        logLevel = "info")
@EnableEntityDefinedRegions(basePackageClasses = Track.class
        , clientRegionShortcut = ClientRegionShortcut.PROXY)
@EnableGemfireRepositories
@EnablePdx
public class GeodeConfiguration {


}

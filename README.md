# Gemfire Spring-Boot example

## Prerequisites

Use Brew to install Geode.

## Start and configure Gemfire

```bash
gfsh>start locator --name=LocatorOne --log-level=config
```

>capture server host and port

```bash
gfsh>start server --name=ServerOne --log-level=config
```

>capture locator host an port

```bash
gfsh>create region --name=Tracks
```

## Gemfire pool configuration

Host and Port for `server` and `locator` are configured in application.properties

```properties
gemfire.cache.server.host=localhost
gemfire.cache.server.port=40404
```
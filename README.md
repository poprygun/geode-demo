# Gemfire Spring-Boot example

## Prerequisites

Use Brew to install Geode.
I use tags to identify code working in `Gemfire`, `Geode`, `PCC`


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
gfsh>create region --name=Tracks --type=REPLICATE
```

## Gemfire pool configuration

Host and Port for `server` and `locator` are configured in application.properties

```properties
spring.data.gemfire.pool.locators=localhost[10334]
spring.data.gemfire.security.username=myuser
spring.data.gemfire.security.password=mypassword
```

Alternatively, annotations could be used

```java
@ClientCacheApplication(name = "AccessingDataGemFireApplication",
        logLevel = "info"
        , servers = {@ClientCacheApplication.Server(host = "localhost", port = 40404)}
        , locators = {@ClientCacheApplication.Locator(host = "localhost", port = 10334)
})

```

## Note on tagging

```bash
git tag -a v0.0.1 -m "Gemfire implementation"
git push --tags
```

So if `Gemfire` version is needed...

```bash
git checkout v0.0.1
```

Or need to delete tag

```bash
git tag -d v0.0.1
git push --delete origin v0.0.1
```
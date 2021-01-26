package org.rif.lumino.explorer.boot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConfigurationProperties("lumino.explorer.hub")
@Configuration
public class LuminoHubProperties {

    private boolean useDefaults;

    private Map<String, String> url;
    private Map<String, Boolean> infiniteCapacity;
    private Map<String, Long> maxConnections;

    public boolean isUseDefaults() {
        return useDefaults;
    }

    public void setUseDefaults(boolean useDefaults) {
        this.useDefaults = useDefaults;
    }

    public Map<String, String> getUrl() {
        return url;
    }

    public void setUrl(Map<String, String> url) {
        this.url = url;
    }

    public Map<String, Boolean> getInfiniteCapacity() {
        return infiniteCapacity;
    }

    public void setInfiniteCapacity(Map<String, Boolean> infiniteCapacity) {
        this.infiniteCapacity = infiniteCapacity;
    }

    public Map<String, Long> getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(Map<String, Long> maxConnections) {
        this.maxConnections = maxConnections;
    }
}

package org.rif.lumino.explorer.boot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConfigurationProperties("lumino.explorer.hub")
@Configuration
public class LuminoHubProperties {

    private long maxConnections;

    private boolean useDefaults;

    private Map<String, String> urls;

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    public long getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(long maxConnections) {
        this.maxConnections = maxConnections;
    }

    public boolean isUseDefaults() {
        return useDefaults;
    }

    public void setUseDefaults(boolean useDefaults) {
        this.useDefaults = useDefaults;
    }
}

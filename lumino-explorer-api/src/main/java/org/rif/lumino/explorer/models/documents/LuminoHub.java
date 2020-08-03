package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("lumino_hub")
public class LuminoHub {

    public static final int MAX_CONNECTIONS = 50;

    @Id
    private String id;

    private String url;

    private Integer connectionCount;

    public LuminoHub() {
    }

    public LuminoHub(String url) {
        this.url = url;
        this.connectionCount = 0;
    }

    public LuminoHub(String url, int connectionCount) {
        this.url = url;
        this.connectionCount = connectionCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(Integer connectionCount) {
        this.connectionCount = connectionCount;
    }

    public void increaseConnectionCount() {
        connectionCount++;
    }
}

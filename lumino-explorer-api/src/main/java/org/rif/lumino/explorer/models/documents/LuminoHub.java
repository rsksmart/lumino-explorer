package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("lumino_hub")
public class LuminoHub {

    @Id
    private String id;

    private String url;

    private Set<String> connectedClients;

    private long connectionCount;

    private long maxConnectionsAllowed;

    public LuminoHub() {
        this.connectedClients = new HashSet<>();
        this.connectionCount = 0;
        this.maxConnectionsAllowed = -1;
    }

    public LuminoHub(String url) {
        this.url = url;
        this.connectedClients = new HashSet<>();
        this.connectionCount = 0;
        this.maxConnectionsAllowed = -1;
    }

    public LuminoHub(String url, HashSet<String> connectedClients) {
        this.url = url;
        this.connectedClients = connectedClients;
        this.connectionCount = connectedClients.size();
        this.maxConnectionsAllowed = -1;
    }

    public LuminoHub(String url, long maxConnectionsAllowed) {
        this.url = url;
        this.connectedClients = new HashSet<>();
        this.connectionCount = 0;
        this.maxConnectionsAllowed = maxConnectionsAllowed;
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

    public Set<String> getConnectedClients() {
        return connectedClients;
    }

    public void addConnection(String address) {
        this.connectedClients.add(address);
        this.connectionCount++;
    }

    public void removeConnection(String address) {
        this.connectedClients.remove(address);
        this.connectionCount--;
    }

    public void setConnectedClients(Set<String> connectedClients) {
        this.connectedClients = connectedClients;
        this.connectionCount = connectedClients.size();
    }

    public long getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(long connectionCount) {
        this.connectionCount = connectionCount;
    }

    public long getMaxConnectionsAllowed() {
        return maxConnectionsAllowed;
    }

    public void setMaxConnectionsAllowed(long maxConnectionsAllowed) {
        this.maxConnectionsAllowed = maxConnectionsAllowed;
    }
}

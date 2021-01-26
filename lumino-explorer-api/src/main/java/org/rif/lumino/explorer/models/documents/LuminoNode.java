package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "lumino_node")
public class LuminoNode {

    @Id
    private String nodeAddress;

    private String rnsAddress;

    private List<String> nodeChannelsIds;

    private Date lastAliveSignal;

    public LuminoNode() {
    }

    public LuminoNode(String nodeAddress, String rnsAddress, List<String> nodeChannelsIds, Date lastAliveSignal) {
        this.nodeAddress = nodeAddress;
        this.rnsAddress = rnsAddress;
        this.nodeChannelsIds = nodeChannelsIds;
        this.lastAliveSignal = lastAliveSignal;
    }

    public Date getLastAliveSignal() {
        return lastAliveSignal;
    }

    public void setLastAliveSignal(Date lastAliveSignal) {
        this.lastAliveSignal = lastAliveSignal;
    }

    public String getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(String nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public String getRnsAddress() {
        return rnsAddress;
    }

    public void setRnsAddress(String rnsAddress) {
        this.rnsAddress = rnsAddress;
    }

    public List<String> getNodeChannelsIds() {
        return nodeChannelsIds;
    }

    public void setNodeChannelsIds(List<String> nodeChannelsIds) {
        this.nodeChannelsIds = nodeChannelsIds;
    }

}

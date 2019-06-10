package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "lumino_node")
public class LuminoNode {

    @Id
    private String nodeAddress;

    private String nodeEndpoint;

    private String rnsAddress;

    private List<String> nodeChannelsIds;

    public LuminoNode() {
    }

    public LuminoNode(
            String nodeAddress,
            String nodeEndpoint,
            String rnsAddress,
            List<String> nodeChannelsIds) {
        this.nodeAddress = nodeAddress;
        this.nodeEndpoint = nodeEndpoint;
        this.rnsAddress = rnsAddress;
        this.nodeChannelsIds = nodeChannelsIds;
    }

    public String getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(String nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public String getNodeEndpoint() {
        return nodeEndpoint;
    }

    public void setNodeEndpoint(String nodeEndpoint) {
        this.nodeEndpoint = nodeEndpoint;
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

package org.rif.lumino.explorer.models.documents;

import org.rif.lumino.explorer.models.enums.ChannelState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "channel")
public class Channel {

    @Id
    private String id;
    private String tokenNetworkAddress;
    private String participantOneAddress;
    private String participantTwoAddress;
    private ChannelState channelState;
    private BigInteger totalDeposit;

    public Channel(
            String id,
            String tokenNetworkAddress,
            String participantOneAddress,
            String participantTwoAddress,
            ChannelState channelState,
            BigInteger totalDeposit) {
        this.id = id;
        this.tokenNetworkAddress = tokenNetworkAddress;
        this.participantOneAddress = participantOneAddress;
        this.participantTwoAddress = participantTwoAddress;
        this.channelState = channelState;
        this.totalDeposit = totalDeposit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParticipantOneAddress() {
        return participantOneAddress;
    }

    public void setParticipantOneAddress(String participantOneAddress) {
        this.participantOneAddress = participantOneAddress;
    }

    public String getParticipantTwoAddress() {
        return participantTwoAddress;
    }

    public void setParticipantTwoAddress(String participantTwoAddress) {
        this.participantTwoAddress = participantTwoAddress;
    }

    public ChannelState getChannelState() {
        return channelState;
    }

    public void setChannelState(ChannelState channelState) {
        this.channelState = channelState;
    }

    public String getTokenNetworkAddress() {
        return tokenNetworkAddress;
    }

    public void setTokenNetworkAddress(String tokenNetworkAddress) {
        this.tokenNetworkAddress = tokenNetworkAddress;
    }

    public BigInteger getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(BigInteger totalDeposit) {
        this.totalDeposit = totalDeposit;
    }
}

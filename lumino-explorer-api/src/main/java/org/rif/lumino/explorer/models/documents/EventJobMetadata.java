package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "event_job_metadata")
public class EventJobMetadata {

    @Id
    private String id;
    private BigInteger lastSyncBlockChannels;
    private BigInteger lastSyncBlockTokens;
    private Integer periodOfSchedule;

    public EventJobMetadata(
            String id,
            BigInteger lastSyncBlockChannels,
            BigInteger lastSyncBlockTokens,
            Integer periodOfSchedule) {
        this.id = id;
        this.lastSyncBlockChannels = lastSyncBlockChannels;
        this.lastSyncBlockTokens = lastSyncBlockTokens;
        this.periodOfSchedule = periodOfSchedule;
    }

    public String getId() {
        return id;
    }

    public BigInteger getLastSyncBlockChannels() {
        return lastSyncBlockChannels;
    }

    public BigInteger getLastSyncBlockTokens() {
        return lastSyncBlockTokens;
    }

    public Integer getPeriodOfSchedule() {
        return periodOfSchedule;
    }

    public void setLastSyncBlockChannels(BigInteger lastSyncBlockChannels) {
        this.lastSyncBlockChannels = lastSyncBlockChannels;
    }

    public void setLastSyncBlockTokens(BigInteger lastSyncBlockTokens) {
        this.lastSyncBlockTokens = lastSyncBlockTokens;
    }

    public void setPeriodOfSchedule(Integer periodOfSchedule) {
        this.periodOfSchedule = periodOfSchedule;
    }

    public void setId(String id) {
        this.id = id;
    }
}

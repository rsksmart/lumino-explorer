package org.rif.lumino.explorer.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "feed")
public class Feed {

    @Transient
    public static final String SEQUENCE_NAME = "feed_sequence";

    @Id
    private Long id;
    private String type;
    private Map data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}

package com.sportshub.entity.FireBaseVideo;

import javax.persistence.*;

@Entity
@Table(name="\"fireBaseVideo\"")
public class FireBaseVideoEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="video_seq")
    @SequenceGenerator(name="video_seq",
            sequenceName="VIDEO_USER", allocationSize=1)

    private Long id;
    private String description;
    private String url;
    private Boolean isVisible;


    public FireBaseVideoEntity() {
    }

    public FireBaseVideoEntity(String description, String url, Boolean isVisible) {
        this.description = description;
        this.url = url;
        this.isVisible = isVisible;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}

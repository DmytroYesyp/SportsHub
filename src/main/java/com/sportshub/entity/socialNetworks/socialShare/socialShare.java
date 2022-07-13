package com.sportshub.entity.socialNetworks.socialShare;

import javax.persistence.*;

@Entity
@Table(name="\"socialShare\"")
public class socialShare {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="social_share_seq")
    @SequenceGenerator(name="social_share_seq",
            sequenceName="SEQ_SOCIAL_SHARE", allocationSize=1)
    long id;
    String url;
    String pictogram;

    public socialShare(){}

    public socialShare(String url, String pictogram) {
        this.url = url;
        this.pictogram = pictogram;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictogram() {
        return pictogram;
    }

    public void setPictogram(String pictogram) {
        this.pictogram = pictogram;
    }

    public long getId() {
        return id;
    }
}

package com.sportshub.entity.socialNetworks.socialLogIn;

import javax.persistence.*;

@Entity
@Table(name="\"socialLogIn\"")
public class socialLogIn {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="login_seq_seq")
    @SequenceGenerator(name="login_seq",
            sequenceName="SEQ_SOCIAL_LOGIN", allocationSize=1)
    long id;
    String url;
    String pictogram;

    public socialLogIn() {
    }

    public socialLogIn(String url, String pictogram) {
        this.url = url;
        this.pictogram = pictogram;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}

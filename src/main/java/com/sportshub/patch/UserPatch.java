package com.sportshub.patch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class UserPatch {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String state;
    private String email;
    private String info;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean empty = true;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean firstNameUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean middleNameUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean lastNameUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean phoneUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean stateUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean emailUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean infoUpdated;

    public void setEmail(String email) {
        empty = false;
        emailUpdated = true;

        this.email = email;
    }

    public void setFirstName(String firstName) {
        empty = false;
        firstNameUpdated = true;

        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        empty = false;
        middleNameUpdated = true;

        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        empty = false;
        lastNameUpdated = true;

        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        empty = false;
        phoneUpdated = true;

        this.phone = phone;
    }

    public void setState(String state) {
        empty = false;
        stateUpdated = true;

        this.state = state;
    }

    public void setInfo(String info) {
        empty = false;
        infoUpdated = true;

        this.info = info;
    }
}

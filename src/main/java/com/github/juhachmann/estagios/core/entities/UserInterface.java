package com.github.juhachmann.estagios.core.entities;

import java.time.LocalDateTime;
import java.util.Date;

public interface UserInterface {

    Long getId();
    String getName();
    String getCnpj();
    boolean isIes();
    String getDescription();
    String getMainPhone();
    String getMainEmail();
    String getOtherPhone();
    String getOtherEmail();
    String getAddressLine();
    String getCity();
    String getState();
    String getCountry();
    Date getCreated_at();
    Date getUpdated_at();

}

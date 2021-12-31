package com.android.group.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Appointment {
    private String aUUID;
    private String fUUID;
    private String dUUID;
    private String aDate;
    private String aStart;
    private String aEnd;
    private Double aLat;
    private Double aLong;
    private String f0Content;
    private String doctorContent;
}

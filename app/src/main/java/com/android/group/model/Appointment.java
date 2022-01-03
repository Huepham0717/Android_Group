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
    private String aDate; //chọn date
    private String aStart; //chọn start time
    private String aEnd; // button để end
    // Cho chọn 1 lần
    private Double aLat; // map
    private Double aLong; // map
    private String f0Content;
    private String doctorContent;
}

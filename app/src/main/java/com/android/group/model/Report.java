package com.android.group.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Report {
    private String rUUID;
    private String fUUID;
    private String dUUID;
    private String rTimestamp;
    private String rContent;
}

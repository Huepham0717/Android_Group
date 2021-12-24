package com.android.group.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Doctor {
    private String dUUID;
    private String dUsername;
    private String dPassword;
    private String dName;
    private String dDOB;
    private String dSex;
    private String dPhone;
    private String dPassport;
    private String dAddress;
}

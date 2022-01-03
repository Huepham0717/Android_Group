package com.android.group.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MedicalRecord implements Parcelable {
    private Long medicalRecordId;
    private String patientUUID;
    private String rTimestamp;
    private String rContent;
    private String fVaxStat;
    private String fInfectStat;
    private String fVitalStat;
    private String fEmergency;
    private User user;
    List<Report> reportList;

    public MedicalRecord() {
    }

    protected MedicalRecord(Parcel in) {
        if (in.readByte() == 0) {
            medicalRecordId = null;
        } else {
            medicalRecordId = in.readLong();
        }
        patientUUID = in.readString();
        rTimestamp = in.readString();
        rContent = in.readString();
        fVaxStat = in.readString();
        fInfectStat = in.readString();
        fVitalStat = in.readString();
        fEmergency = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        reportList = in.createTypedArrayList(Report.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (medicalRecordId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(medicalRecordId);
        }
        dest.writeString(patientUUID);
        dest.writeString(rTimestamp);
        dest.writeString(rContent);
        dest.writeString(fVaxStat);
        dest.writeString(fInfectStat);
        dest.writeString(fVitalStat);
        dest.writeString(fEmergency);
        dest.writeParcelable(user, flags);
        dest.writeTypedList(reportList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MedicalRecord> CREATOR = new Creator<MedicalRecord>() {
        @Override
        public MedicalRecord createFromParcel(Parcel in) {
            return new MedicalRecord(in);
        }

        @Override
        public MedicalRecord[] newArray(int size) {
            return new MedicalRecord[size];
        }
    };

    public Long getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(Long medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public String getPatientUUID() {
        return patientUUID;
    }

    public void setPatientUUID(String patientUUID) {
        this.patientUUID = patientUUID;
    }

    public String getrTimestamp() {
        return rTimestamp;
    }

    public void setrTimestamp(String rTimestamp) {
        this.rTimestamp = rTimestamp;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public String getfVaxStat() {
        return fVaxStat;
    }

    public void setfVaxStat(String fVaxStat) {
        this.fVaxStat = fVaxStat;
    }

    public String getfInfectStat() {
        return fInfectStat;
    }

    public void setfInfectStat(String fInfectStat) {
        this.fInfectStat = fInfectStat;
    }

    public String getfVitalStat() {
        return fVitalStat;
    }

    public void setfVitalStat(String fVitalStat) {
        this.fVitalStat = fVitalStat;
    }

    public String getfEmergency() {
        return fEmergency;
    }

    public void setfEmergency(String fEmergency) {
        this.fEmergency = fEmergency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public static Creator<MedicalRecord> getCREATOR() {
        return CREATOR;
    }
}

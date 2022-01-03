package com.android.group.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Report implements Parcelable {
    private Long reportId;
    private String patientUUID;
    private String temperature;
    private String cough;
    private String breath;
    private String sThroat;
    private String pneumonia;
    private String diseases;
    private String symptoms;
    private String rTimestamp;
    private MedicalRecord medicalRecord;

    public Report() {
    }

    protected Report(Parcel in) {
        if (in.readByte() == 0) {
            reportId = null;
        } else {
            reportId = in.readLong();
        }
        patientUUID = in.readString();
        temperature = in.readString();
        cough = in.readString();
        breath = in.readString();
        sThroat = in.readString();
        pneumonia = in.readString();
        diseases = in.readString();
        symptoms = in.readString();
        rTimestamp = in.readString();
        medicalRecord = in.readParcelable(MedicalRecord.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (reportId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(reportId);
        }
        dest.writeString(patientUUID);
        dest.writeString(temperature);
        dest.writeString(cough);
        dest.writeString(breath);
        dest.writeString(sThroat);
        dest.writeString(pneumonia);
        dest.writeString(diseases);
        dest.writeString(symptoms);
        dest.writeString(rTimestamp);
        dest.writeParcelable(medicalRecord, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getBreath() {
        return breath;
    }

    public void setBreath(String breath) {
        this.breath = breath;
    }

    public String getsThroat() {
        return sThroat;
    }

    public void setsThroat(String sThroat) {
        this.sThroat = sThroat;
    }

    public String getPneumonia() {
        return pneumonia;
    }

    public void setPneumonia(String pneumonia) {
        this.pneumonia = pneumonia;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getrTimestamp() {
        return rTimestamp;
    }

    public void setrTimestamp(String rTimestamp) {
        this.rTimestamp = rTimestamp;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getPatientUUID() {
        return patientUUID;
    }

    public void setPatientUUID(String patientUUID) {
        this.patientUUID = patientUUID;
    }

    public static Creator<Report> getCREATOR() {
        return CREATOR;
    }
}

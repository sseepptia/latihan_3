package com.yeni.laporanmasyarakat1.model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

@IgnoreExtraProperties
public class Report implements Serializable {
    private String title;
    private String asal;
    private String report;
    private String key;
    public Report (){
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {

        this.key = key;
    }
    public String getTitle() {

        return title;
    }
    public void setTitle(String title) {

        this.title = title;
    }
    public String getAsal() {

        return asal;
    }
    public void setAsal(String asal) {

        this.asal = asal;
    }
    public String getReport() {

        return report;
    }
    public void setReport(String report) {

        this.report = report;
    }
    @Override
    public String toString() {
        return " "+title+"\n" +
                " "+asal +"\n" +
                " "+report;
    }
    public Report(String tt, String as, String re){
        title = tt;
        asal = as;
        report = re;

    }
}

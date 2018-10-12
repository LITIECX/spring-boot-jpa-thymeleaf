package com.neo.dataHandle;

public class TableData {
    private String locationId; //课程在课表位置
    private int rowspan;   //夸几节
    private String content;   //内容

    public TableData(String locationId, int rowspan, String content) {
        this.locationId = locationId;
        this.rowspan = rowspan;
        this.content = content;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }
}

package org.gerejajkt.remaja.domain.viewparam;

import org.gerejajkt.remaja.model.Attendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wurongqiang on 5/5/17.
 */

public class AttendanceViewParam {

    private int id;
    private String meetingName;
    private Date meetingDate;
    private String sessionName;

    public static List<AttendanceViewParam> create(List<Attendance> attendances) {
        List<AttendanceViewParam> list = new ArrayList<>();

        for (Attendance attendance : attendances) {
            list.add(create(attendance));
        }
        return list;
    }

    private static AttendanceViewParam create(Attendance attendance) {

        AttendanceViewParam data = new AttendanceViewParam();
        data.id = attendance.getId();
        data.meetingName = attendance.getMeetingName();
        data.meetingDate = attendance.getMeetingDate();
        data.sessionName = attendance.getSessionName();

        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}

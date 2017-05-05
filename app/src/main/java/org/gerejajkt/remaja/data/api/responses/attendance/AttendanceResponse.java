package org.gerejajkt.remaja.data.api.responses.attendance;

import com.google.gson.annotations.SerializedName;
import org.gerejajkt.remaja.model.Attendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huteri on 5/3/17.
 */

public class AttendanceResponse {

    public int id;

    @SerializedName("meeting_name")
    public String meetingName;

    @SerializedName("meeting_date")
    public Date meetingDate;

    @SerializedName("session_name")
    public String sessionName;

    public Attendance toAttendance () {
        Attendance attendance = new Attendance();
        attendance.setId(id);
        attendance.setMeetingName(meetingName);
        attendance.setMeetingDate(meetingDate);
        attendance.setSessionName(sessionName);

        return attendance;
    }

    public static List<Attendance> toAttendances(List<AttendanceResponse> attendances) {
        List<Attendance> result = new ArrayList<>();
        
        for (AttendanceResponse attendance : attendances) {
            result.add(attendance.toAttendance());
        }

        return result;
    }
}

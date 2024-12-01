package pairmatching.view.vo;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

public record CourseMissionInput(Course course, Mission mission) {
    public static CourseMissionInput from(Course course, Level level, String missionName) {
        Mission foundMission = Mission.from(missionName, level);
        return new CourseMissionInput(course, foundMission);
    }
}

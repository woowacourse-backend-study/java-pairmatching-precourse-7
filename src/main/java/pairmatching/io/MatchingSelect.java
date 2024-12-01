package pairmatching.io;

import pairmatching.Course;
import pairmatching.Level;
import pairmatching.Mission;

public class MatchingSelect {
    private Course course;
    private Level level;
    private Mission mission;

    public MatchingSelect(String courseString, String levelString, String missionString) {
        course = Course.findByName(courseString);
        level = Level.findByName(levelString);
        mission = Mission.findByName(missionString);
        System.out.println();
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }
}

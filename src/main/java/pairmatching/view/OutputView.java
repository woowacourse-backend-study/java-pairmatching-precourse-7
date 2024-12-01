package pairmatching.view;

import pairmatching.domain.PairCrews;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = " | ";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printCourseInfo() {
        printLine();
        printCourse();
        printMissions();
        printLine();
    }

    private void printLine() {
        printMessage("#############################################");
    }

    private void printCourse() {
        String courses = String.join(DELIMITER, Arrays.stream(Course.values()).map(Course::getName).toList());
        printMessage(String.format("과정: %s", courses));
    }

    private void printMissions() {
        printMessage("미션: ");
        EnumMap<Level, List<Mission>> missions = new EnumMap<>(Level.class);
        for (Level level : Level.values()) {
            missions.put(level, new ArrayList<>());
        }
        for (Mission mission : Mission.values()) {
            Level level = mission.getLevel();
            List<Mission> updated = missions.get(level);
            updated.add(mission);
            missions.put(level, updated);
        }
        for (Level level : Level.values()) {
            String joined = String.join(DELIMITER, missions.get(level).stream().map(Mission::getName).toList());
            printMessage(String.format("    - %s: %s", level.getName(), joined));
        }
    }

    public void printPairs(List<PairCrews> pairs) {
        printMessage("페어 매칭 결과입니다.");
        for (PairCrews pair : pairs) {
            printMessage(pair.getPairString());
        }
    }
}

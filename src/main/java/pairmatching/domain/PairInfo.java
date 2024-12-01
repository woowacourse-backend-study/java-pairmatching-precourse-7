package pairmatching.domain;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

import java.util.List;

public record PairInfo(Course course, Mission mission, List<PairCrews> crews) {
    public Level getLevel() {
        return mission.getLevel();
    }
}

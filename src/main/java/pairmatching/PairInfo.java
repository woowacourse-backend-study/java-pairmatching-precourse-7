package pairmatching;

import java.util.List;

public class PairInfo {
    private Crew crew1;
    private Crew crew2;
    private Course course;
    private Level level;

    public PairInfo(Crew crew1, Crew crew2, Course course, Level level) {
        this.crew1 = crew1;
        this.crew2 = crew2;
        this.course = course;
        this.level = level;
    }

    public boolean validatePair(Pair pair, Level level) {
        if (pair.hasCrews(List.of(crew1, crew2)) && level.equals(level)) {
            return false;
        }
        return true;
    }
}

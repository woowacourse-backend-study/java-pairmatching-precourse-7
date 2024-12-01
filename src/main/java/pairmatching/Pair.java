package pairmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Pair {
    private List<Crew> crews;
    private Course course;
    private Level level;
    private Mission mission;

    public Pair(List<Crew> crews, Course course, Level level, Mission mission) {
        this.crews = crews;
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public List<PairInfo> convertPairInfos() {
        List<PairInfo> pairInfos = new ArrayList<>();
        Crew crew1 = crews.get(0);
        Crew crew2 = crews.get(1);
        pairInfos.add(new PairInfo(crew1, crew2, course, level));

        if (crews.size() == 3) {
            Crew crew3 = crews.get(2);
            pairInfos.add(new PairInfo(crew2, crew3, course, level));
            pairInfos.add(new PairInfo(crew1, crew3, course, level));
        }
        return pairInfos;
    }

    public boolean hasCrews(List<Crew> crews) {
        int count = 0;
        for (Crew crew : this.crews) {
            for (Crew requestCrew : crews) {
                if (requestCrew.equals(crew)) {
                    count++;
                }
            }
        }
        if (count == crews.size()) {
            return true;
        }
        return false;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" : ");
        for (Crew crew : crews) {
            stringJoiner.add(crew.toString());
        }
        return stringJoiner.toString();
    }
}

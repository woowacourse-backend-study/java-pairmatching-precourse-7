package pairmatching.repository;

import pairmatching.domain.PairCrews;
import pairmatching.domain.PairInfo;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

import java.util.ArrayList;
import java.util.List;

public class PairRepository {
    private static final List<PairInfo> pairs = new ArrayList<>();

    public static List<PairCrews> findPairCrews(Course course, Mission mission) {
        return pairs.stream()
                .filter(pairInfo -> pairInfo.course() == course && pairInfo.mission() == mission)
                .findAny()
                .map(PairInfo::crews)
                .orElse(null);
    }

    public static List<PairCrews> findPairCrewsByLevel(Level level) {
        List<PairInfo> results = pairs.stream().filter(pairInfo -> pairInfo.getLevel() == level).toList();
        List<PairCrews> pairCrews = new ArrayList<>();
        for (PairInfo result : results) {
            pairCrews.addAll(result.crews());
        }
        return pairCrews;
    }

    public static void save(Course course, Mission mission, List<PairCrews> pairCrews) {
        pairs.stream()
                .filter(pairInfo -> pairInfo.course() == course && pairInfo.mission() == mission)
                .findAny()
                .ifPresent(pairs::remove);
        pairs.add(new PairInfo(course, mission, pairCrews));
    }

    public static void reset() {
        pairs.clear();
    }
}

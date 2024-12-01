package pairmatching;

import static camp.nextstep.edu.missionutils.Randoms.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.io.MatchingSelect;

public class PairFactory {

    private List<Crew> backendCrews;
    private List<Crew> frontendCrews;
    private PairMemory pairMemory;
    private List<Pair> pairs;

    public PairFactory(List<Crew> backendCrews, List<Crew> frontendCrews) {
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
        this.pairMemory = new PairMemory();
        this.pairs = new ArrayList<>();
    }

    public void endPair(List<Pair> pairs) {
        pairs.forEach(pair -> pairMemory.addMemory(pair));
    }


    public List<Pair> makePairs(MatchingSelect matchingSelect) {
        return makePairs(matchingSelect.getCourse(), matchingSelect.getLevel(), matchingSelect.getMission());
    }

    public List<Pair> makePairs(Course course, Level level, Mission mission) {
        List<Crew> crews = new ArrayList<>();
        List<Pair> pairs;

        if (course.equals(Course.BACKEND)) {
            crews.addAll(backendCrews);
        } else {
            crews.addAll(frontendCrews);
        }
        int index = 0;
        do {
            pairs = new ArrayList<>();
            crews = shuffle(crews);
            while (index < crews.size()) {
                Crew firstCrew = crews.get(index);
                index++;
                Crew secondCrew = crews.get(index);
                index++;
                if (index + 1 == crews.size()) {
                    Crew thirdCrew = crews.get(index);
                    index++;
                    List<Crew> crewPair = makeCrewList(firstCrew, secondCrew, thirdCrew);
                    Pair pair = new Pair(crewPair, course, level, mission);
                    pairs.add(pair);
                    continue;
                }

                List<Crew> crewPair = makeCrewList(firstCrew, secondCrew);
                Pair pair = new Pair(crewPair, course, level, mission);
                pairs.add(pair);
            }
        } while (!pairMemory.validatePair(pairs, level));
        this.pairs = pairs;
        return pairs;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void resetPairs() {
        pairs = new ArrayList<>();
        pairMemory.removeAll();
    }

    private List<Crew> makeCrewList(Crew... crews) {
        List<Crew> resultCrews = new ArrayList<>(Arrays.asList(crews));
        return resultCrews;
    }
}

package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.io.InputView;
import pairmatching.io.MatchingSelect;
import pairmatching.io.OutputView;

public class PairFactory {

    private List<Crew> backendCrews;
    private List<Crew> frontendCrews;
    private PairMemory pairMemory;
    private List<Pair> pairs;
    private InputView inputView;
    private OutputView outputView;

    public PairFactory(List<Crew> backendCrews, List<Crew> frontendCrews, InputView inputView,
                       OutputView outputView) {
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
        this.pairMemory = new PairMemory();
        this.pairs = new ArrayList<>();
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void endPair(List<Pair> pairs) {
        pairs.forEach(pair -> pairMemory.addMemory(pair));
    }


    public List<Pair> makePairs() {
        MatchingSelect matchingSelect = inputView.inputMatchingFunction();
        return makePairs(matchingSelect.getCourse(), matchingSelect.getLevel(), matchingSelect.getMission());
    }

    public List<Pair> makePairs(Course course, Level level, Mission mission) {
        List<Crew> crews = getCoursePair(course);
        List<Pair> pairs;
        List<String> stringList = new ArrayList<>();
        for (Crew crew : crews) {
            stringList.add(crew.getName());
        }
        List<String> shuffle = Randoms.shuffle(stringList);
        crews = shuffle.stream().map(Crew::new).toList();
        do {
            //crews = Randoms.shuffle(crews);
            pairs = makePair(crews, course, level, mission);

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
        List<Crew> resultCrews = new ArrayList<>();
        resultCrews.addAll(Arrays.asList(crews));
        return resultCrews;
    }

    private List<Crew> getCoursePair(Course course) {
        List<Crew> crews = new ArrayList<>();
        if (course.equals(Course.BACKEND)) {
            crews.addAll(backendCrews);
        } else {
            crews.addAll(frontendCrews);
        }
        return crews;
    }

    private List<Pair> makePair(List<Crew> crews, Course course, Level level, Mission mission) {
        int index = 0;

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
        return pairs;
    }
}

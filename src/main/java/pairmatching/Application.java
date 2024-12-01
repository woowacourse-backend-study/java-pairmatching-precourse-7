package pairmatching;

import java.util.List;
import pairmatching.io.FileReader;
import pairmatching.io.InputView;
import pairmatching.io.MatchingSelect;
import pairmatching.io.OutputView;

public class Application {
    public static void main(String[] args) {
        CrewFactory crewFactory = new CrewFactory();
        FileReader fileReader = new FileReader(crewFactory);
        List<Crew> backendCrews = fileReader.readProduct("/backend-crew.md");
        List<Crew> frontendCrews = fileReader.readProduct("/frontend-crew.md");
        PairFactory pairFactory = new PairFactory(backendCrews, frontendCrews);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String select = inputView.inputManFunction();
        if (select.equals("1")) {
            MatchingSelect matchingSelect = inputView.inputMatchingFunction();
            List<Pair> resultPair = pairFactory.makePairs(matchingSelect);
            outputView.outputPair(resultPair);
            pairFactory.endPair(resultPair);

        } else if (select.equals("2")) {
            List<Pair> pairs = pairFactory.getPairs();
            outputView.outputPair(pairs);
        } else if (select.equals("3")) {
            outputView.outputReset();
            pairFactory.resetPairs();
        }


    }
}

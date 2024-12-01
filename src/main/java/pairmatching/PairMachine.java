package pairmatching;

import java.util.List;
import pairmatching.io.InputView;
import pairmatching.io.OutputView;

public class PairMachine {

    private InputView inputView;
    private OutputView outputView;
    private PairFactory pairFactory;

    public PairMachine(InputView inputView, OutputView outputView, List<Crew> backendCrews,
                       List<Crew> frontendCrews) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairFactory = new PairFactory(backendCrews, frontendCrews, inputView, outputView);
    }

    public void process() {
        while (true) {
            String select = inputView.inputManFunction();
            if (select.equals("Q")) {
                return;
            }
            if (select.equals("1")) {
                matchingPairs();
            }
            if (select.equals("2")) {
                searchRecentPairs();
            }
            if (select.equals("3")) {
                resetPairs();
            }
        }
    }

    private void matchingPairs() {

        List<Pair> resultPair = pairFactory.makePairs();
        outputView.outputPair(resultPair);
        pairFactory.endPair(resultPair);
    }

    private void searchRecentPairs() {
        List<Pair> pairs = pairFactory.getPairs();
        outputView.outputPair(pairs);
    }

    private void resetPairs() {
        outputView.outputReset();
        pairFactory.resetPairs();
    }
}

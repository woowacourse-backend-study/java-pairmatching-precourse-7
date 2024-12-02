package pairmatching.controller.subController;

import java.util.List;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchController implements Controllable {
    private final InputView inputView;
    private final OutputView outputView;

    public MatchController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public void process() {
        OutputView.printMatchStart();
        List<String> randomCrews = inputView.readOption();
        OutputView.printPairMatch(randomCrews);
    }


}

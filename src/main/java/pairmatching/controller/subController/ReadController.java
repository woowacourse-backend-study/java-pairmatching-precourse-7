package pairmatching.controller.subController;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class ReadController implements Controllable {
    private final InputView inputView;
    private final OutputView outputView;

    public ReadController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }


    @Override
    public void process() {
        inputView.readOption2();

    }
}

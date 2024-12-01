package pairmatching.controller;

import pairmatching.service.PairMatchingService;
import pairmatching.view.OutputView;

public class PairResetController implements Controllable {
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public PairResetController(
            OutputView outputView,
            PairMatchingService pairMatchingService
    ) {
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void process() {
        pairMatchingService.reset();
        outputView.printMessage("초기화 되었습니다.");
    }
}

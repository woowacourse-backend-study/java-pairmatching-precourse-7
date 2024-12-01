package pairmatching.controller;

import pairmatching.domain.PairCrews;
import pairmatching.service.PairMatchingService;
import pairmatching.service.dto.PairMatchingRequest;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.vo.CourseMissionInput;

import java.util.List;
import java.util.function.Supplier;

public class ViewPairMatchingController implements Controllable {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public ViewPairMatchingController(
            InputView inputView,
            OutputView outputView,
            PairMatchingService pairMatchingService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
    }


    @Override
    public void process() {
        CourseMissionInput courseMissionInput = readInput();
        List<PairCrews> pairs = pairMatchingService.getPairs(PairMatchingRequest.of(courseMissionInput));
        outputView.printPairs(pairs);
    }

    private CourseMissionInput readInput() {
        outputView.printCourseInfo();
        return doLoop(inputView::readSubjectInfo);
    }

    private  <T> T doLoop(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}

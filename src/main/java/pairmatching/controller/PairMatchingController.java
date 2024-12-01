package pairmatching.controller;

import pairmatching.domain.PairCrews;
import pairmatching.infrastructure.CustomException;
import pairmatching.service.PairMatchingService;
import pairmatching.service.dto.PairMatchingRequest;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.vo.Answer;
import pairmatching.view.vo.CourseMissionInput;

import java.util.List;
import java.util.function.Supplier;

public class PairMatchingController implements Controllable {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public PairMatchingController(
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
        CourseMissionInput missionInput = readInput();
        boolean alreadyExist = pairMatchingService.isAlreadyExist(PairMatchingRequest.of(missionInput));
        if (alreadyExist) {
            boolean continued = readContinue();
            if (!continued) {
                return;
            }
        }
        try {
            List<PairCrews> generated = pairMatchingService.generate(PairMatchingRequest.of(missionInput));
            outputView.printPairs(generated);
        } catch (CustomException e) {
            outputView.printMessage(e.getMessage());
        }
    }

    private CourseMissionInput readInput() {
        outputView.printCourseInfo();
        return doLoop(inputView::readSubjectInfo);
    }

    private boolean readContinue() {
        Answer answer = doLoop(inputView::readMatchContinue);
        return answer.isPositive();
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

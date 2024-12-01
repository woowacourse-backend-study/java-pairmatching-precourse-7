package pairmatching.controller;

import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class MainController {
    private final Map<MainOption, Controllable> controllers;
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public MainController(InputView inputView, OutputView outputView, PairMatchingService pairMatchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
        this.controllers = new EnumMap<>(MainOption.class);
        initializeControllers();
    }

    private void initializeControllers() {
        controllers.put(MainOption.PAIR_MATCHING,
                new PairMatchingController(
                        inputView,
                        outputView,
                        pairMatchingService
                )
        );
        controllers.put(MainOption.VIEW_PAIR,
                new ViewPairMatchingController(
                        inputView,
                        outputView,
                        pairMatchingService
                )
        );
        controllers.put(MainOption.RESET_PAIR,
                new PairResetController(
                        outputView,
                        pairMatchingService
                )
        );
    }

    public void run() {
        new InitializingController().process(); // initializing 로직이 있는 경우 (초기화)
        MainOption mainOption;
        do {
            mainOption = doLoop(inputView::readMainOption);
            process(mainOption);
        } while (mainOption.isPlayable());
    }

    private void process(MainOption mainOption) {
        try {
            controllers.get(mainOption).process();
        } catch (NullPointerException ignored) {
            // 필요하면 예외 처리 (APPLICATION_EXIT은 안 만들어도 됨)
        }
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

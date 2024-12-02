package pairmatching.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import pairmatching.controller.subController.Controllable;
import pairmatching.controller.subController.ExitController;
import pairmatching.controller.subController.InitializeController;
import pairmatching.controller.subController.MatchController;
import pairmatching.controller.subController.ReadController;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.option.MainOption;
import pairmatching.domain.repository.BackendRepository;
import pairmatching.domain.repository.CrewRepository;
import pairmatching.domain.repository.FrontendRepository;
import pairmatching.domain.repository.MatchingHistoryRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MainController {
    private final InputView inputview;
    private final OutputView outputView;
    private final Map<MainOption, Controllable> controllers;

    public MainController(InputView inputview, OutputView outputView) {
        this.inputview = inputview;
        this.outputView = outputView;
        this.controllers = new EnumMap<>(MainOption.class);
        initializeBackendCrew();
        initializeFrontendCrew();
        initailizeMatchingHistory();
        initializeControllers();
    }

    private void initailizeMatchingHistory() {
        for (Level level : Level.values()) {
            MatchingHistoryRepository.add(new MatchingHistory(level));
        }
    }

    private void initializeBackendCrew() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/backend-crew.md"))) {
            initializeCrews(reader, Course.BACKEND);
        } catch (IOException e) {
            System.out.println("파일 읽는 중 에러 발생");
        }
    }

    private void initializeFrontendCrew() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/frontend-crew.md"))) {
            initializeCrews(reader, Course.FRONTEND);
        } catch (IOException e) {
            System.out.println("파일 읽는 중 에러 발생");
        }
    }

    private void initializeCrews(BufferedReader reader, Course course) throws IOException {
        String name;
        while ((name = reader.readLine()) != null) {
            if (!name.trim().isEmpty()) {
                Crew crew = new Crew(name, course);
                if (crew.getCourse() == Course.BACKEND) {
                    BackendRepository.add(crew);
                    CrewRepository.add(crew);
                } else {
                    FrontendRepository.add(crew);
                    CrewRepository.add(crew);
                }
            }
        }
    }

    private void initializeControllers() {
        controllers.put(MainOption.MATCH_PAIR, new MatchController(inputview, outputView));
        controllers.put(MainOption.READ_PAIR, new ReadController(inputview, outputView));
        controllers.put(MainOption.INITIALIZE_PAIR, new InitializeController());
        controllers.put(MainOption.EXIT,new ExitController());
    }

    public void process() {
        MainOption option;
        do {
            outputView.printMainOption();
            option = doLoop(() -> MainOption.from(inputview.enterSelectOption()));
            controllers.get(option).process();
        } while (option.isPlayable());
    }

    private <T> T doLoop(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}

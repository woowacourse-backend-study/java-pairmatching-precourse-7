package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.Mission;
import pairmatching.domain.repository.BackendRepository;
import pairmatching.domain.repository.CrewRepository;
import pairmatching.domain.repository.FrontendRepository;
import pairmatching.domain.repository.MatchingHistoryRepository;
import pairmatching.domain.util.Util;

public class InputView {

    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public String enterSelectOption() {
        return readLine().strip();
    }

    //    프론트엔드, 레벨1, 자동차경주
    public List<String> readOption() {
        try {
            List<String> options = Util.splitByComma(Console.readLine());
            validateOptionNumber(options);

            //코스검증
            Course course = validateCourse(options.get(0));
            //레벨검증
            Level level = validateLevel(options.get(1));
            //미션검증
            Mission mission = validateMission(level, options.get(2));

            List<String> crews = getCrews(course).stream().map(Crew::getName).toList();
            List<String> randomCrews = Randoms.shuffle(crews);

            isAvailableMathch(randomCrews, level.toString());
            MatchingHistory.addMatch(randomCrews);
            return randomCrews;

        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return readOption();
        }

    }

    public List<String> readOption2() {
        try {
            List<String> options = Util.splitByComma(Console.readLine());
            validateOptionNumber(options);

            Course course = validateCourse(options.get(0));
            Level level = validateLevel(options.get(1));
            Mission mission = validateMission(level, options.get(2));

            MatchingHistory matchingHistory = MatchingHistoryRepository.findHistoryByLevel(level.toString());
            return List.of("11");

        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return readOption();
        }

    }

    private void isAvailableMathch(List<String> randomCrews, String level) {
        MatchingHistory matchingHistory = MatchingHistoryRepository.findHistoryByLevel(level);
        if (randomCrews.size() % 2 == 1) {
            for (int i = 0; i < randomCrews.size() - 3; i += 2) {
                System.out.println(randomCrews.get(i));
                Crew crew1 = CrewRepository.findMenuByName(randomCrews.get(i));
                Crew crew2 = CrewRepository.findMenuByName(randomCrews.get(i + 1));
                if (!MatchingHistory.validMatch(crew1, crew2)) {
                    isAvailableMathch(randomCrews, level);
                }
            }

        } else {
            for (int i = 0; i < randomCrews.size(); i += 2) {
                Crew crew1 = CrewRepository.findMenuByName(randomCrews.get(i));
                Crew crew2 = CrewRepository.findMenuByName(randomCrews.get(i + 1));
                if (!MatchingHistory.validMatch(crew1, crew2)) {
                    isAvailableMathch(randomCrews, level);
                }
            }
        }
    }

    private List<Crew> getCrews(Course course) {
        if (course == Course.BACKEND) {
            return BackendRepository.crews();
        } else {
            return FrontendRepository.crews();
        }
    }

    private void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    private Mission validateMission(Level level, String inputMission) {
        List<Mission> missions = level.getMissions();
        for (Mission mission : missions) {
            if (mission.getName().equals(inputMission)) {
                return mission;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
    }

    private Level validateLevel(String inputLevel) {
        for (Level level : Level.values()) {
            if (level.getName().equals(inputLevel)) {
                return level;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 레벨입니다.");
    }

    private Course validateCourse(String inputCourse) {
        for (Course course : Course.values()) {
            if (course.getName().equals(inputCourse)) {
                return course;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 분야입니다.");
    }

    private void validateOptionNumber(List<String> options) {
        if (!(options.size() == 3)) {
            throw new IllegalArgumentException("잘못된 옵션값을 입력하셨습니다");
        }
    }

}

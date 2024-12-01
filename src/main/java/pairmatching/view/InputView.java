package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.controller.MainOption;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;
import pairmatching.view.vo.Answer;
import pairmatching.view.vo.CourseMissionInput;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String YES = "네";
    private static final String NO = "아니오";

    public MainOption readMainOption() {
        System.out.println("기능을 선택하세요.");
        String functions = String.join("\n",
                Arrays.stream(MainOption.values())
                        .map(MainOption::getText)
                        .toList());
        System.out.println(functions);
        return MainOption.from(Console.readLine().trim());
    }

    public CourseMissionInput readSubjectInfo() {
        System.out.println("과정, 레벨, 미션을 선택하세요");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
        String input = Console.readLine().trim();
        List<String> parsed = Arrays.stream(input.split(DELIMITER)).map(String::trim).toList();

        validateCourseInput(parsed);
        Course course = Course.from(parsed.getFirst());
        Level level = Level.from(parsed.get(1));
        String name = parsed.get(2);
        return CourseMissionInput.from(course, level, name);
    }

    private void validateCourseInput(List<String> parsed) {
        if (parsed.size() != 3) {
            throw new CustomException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    public Answer readMatchContinue() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println(String.join(" | ", Arrays.stream(Answer.values()).map(Answer::getAnswer).toList()));
        return Answer.from(Console.readLine().trim());
    }
}

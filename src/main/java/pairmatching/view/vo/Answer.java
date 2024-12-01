package pairmatching.view.vo;

import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;

import java.util.Arrays;

public enum Answer {
    YES("네", true),
    NO("아니오", false),
    ;

    private final String answer;
    private final boolean positive;

    private Answer(String answer, boolean positive) {
        this.answer = answer;
        this.positive = positive;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isPositive() {
        return positive;
    }

    public static Answer from(String answer) {
        return Arrays.stream(values())
                .filter(v -> v.answer.equals(answer))
                .findAny()
                .orElseThrow(() -> new CustomException(ExceptionMessage.INVALID_INPUT.getMessage()));
    }
}

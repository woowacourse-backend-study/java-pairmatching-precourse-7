package pairmatching.domain.option;

import java.util.Arrays;

public enum MainOption {
    MATCH_PAIR("1"),
    READ_PAIR("2"),
    INITIALIZE_PAIR("3"),
    EXIT("Q");

    private final String option;

    MainOption(String option) {
        this.option = option;
    }

    public boolean isPlayable() {
        return this != EXIT;
    }

    public static MainOption from(String input) {
        return Arrays.stream(MainOption.values())
                .filter(option -> option.getOption().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다"));
    }

    private Object getOption() {
        return option;
    }
}

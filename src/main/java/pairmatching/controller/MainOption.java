package pairmatching.controller;

import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;

import java.util.Arrays;

public enum MainOption {

    PAIR_MATCHING("1", "1. 페어 매칭"),
    VIEW_PAIR("2", "2. 페어 조회"),
    RESET_PAIR("3", "3. 페어 초기화"),
    EXIT("Q", "Q. 종료"),
    ;

    private final String command;
    private final String text;

    MainOption(String command, String text) {
        this.command = command;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isPlayable() {
        return this != EXIT;
    }

    public static MainOption from(String command) {
        return Arrays.stream(MainOption.values())
                .filter(option -> option.command.equals(command))
                .findAny()
                .orElseThrow(() -> new CustomException(ExceptionMessage.NO_MAIN_OPTION.getMessage()));
    }
}

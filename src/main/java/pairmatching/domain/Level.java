package pairmatching.domain;

import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level from(String name) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(name))
                .findAny()
                .orElseThrow(() -> new CustomException(ExceptionMessage.NO_EXIST_LEVEL.getMessage()));
    }
}
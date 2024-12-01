package pairmatching.domain.vo;

import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course from(String name) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(name))
                .findAny()
                .orElseThrow(() -> new CustomException(ExceptionMessage.NO_EXIST_COURSE.getMessage()));
    }
}

package pairmatching.domain.vo;

import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;

import java.util.Arrays;

public enum Mission {

    자동차경주("자동차경주", Level.LEVEL1),
    로또("로또", Level.LEVEL1),
    숫자야구게임("숫자야구게임", Level.LEVEL1),

    장바구니("장바구니", Level.LEVEL2),
    결제("결제", Level.LEVEL2),
    지하철노선도("지하철노선도", Level.LEVEL2),

    성능개선("성능개선", Level.LEVEL4),
    배포("배포", Level.LEVEL4),
    ;

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public static Mission from(String name, Level level) {
        return Arrays.stream(values())
                .filter(mission -> mission.name.equals(name) && mission.level == level)
                .findAny()
                .orElseThrow(() -> new CustomException(ExceptionMessage.NO_EXIST_MISSION.getMessage()));
    }
}

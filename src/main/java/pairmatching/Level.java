package pairmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Mission.CAR_RACING, Mission.LOTTO, Mission.NUMBER_BASEBALL),
    LEVEL2("레벨2", Mission.SHOPPING_CART, Mission.PAYMENT, Mission.SUBWAY_MAP),
    LEVEL3("레벨3"),
    LEVEL4("레벨4", Mission.IMPROVED_PERFORMANCE, Mission.DEPLOY),
    LEVEL5("레벨5");

    private String name;
    private List<Mission> missions;

    Level(String name, Mission... missions) {
        this.name = name;
        this.missions = new ArrayList<>();
        this.missions.addAll(Arrays.asList(missions));
    }

    public static Level findByName(String levelName) {
        for (Level level : Level.values()) {
            if (level.name.equals(levelName)) {
                return level;
            }
        }
        throw new RuntimeException("찾을 수 없는 값입니다");
    }
}
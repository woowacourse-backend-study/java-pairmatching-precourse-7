package pairmatching.domain;

import java.util.List;

public enum Level {
    LEVEL1("레벨1", List.of(Mission.of("자동차경주"), Mission.of("로또"), Mission.of("숫자야구게임"))),
    LEVEL2("레벨2", List.of(Mission.of("장바구니"), Mission.of("결제"), Mission.of("지하철노선도"))),
    LEVEL3("레벨3", List.of()),
    LEVEL4("레벨4", List.of(Mission.of("성능개선"), Mission.of("배포"))),
    LEVEL5("레벨5", List.of());

    private String name;
    private List<Mission> missions;

    Level(String name, List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    public String getNam() {
        return name;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public Object getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}

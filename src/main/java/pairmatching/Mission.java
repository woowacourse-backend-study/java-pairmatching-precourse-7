package pairmatching;

public enum Mission {

    CAR_RACING("자동차경주"),
    LOTTO("로또"),
    NUMBER_BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"),
    PAYMENT("결제"),
    SUBWAY_MAP("지하철노선도"),
    IMPROVED_PERFORMANCE("성능개선"),
    DEPLOY("배포");
    private String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission findByName(String missionName) {
        for (Mission mission : Mission.values()) {
            if (mission.name.equals(missionName)) {
                return mission;
            }
        }
        throw new RuntimeException("찾을 수 없는 값입니다");
    }
}
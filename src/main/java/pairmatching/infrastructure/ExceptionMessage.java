package pairmatching.infrastructure;

public enum ExceptionMessage {
    INVALID_INPUT("입력 형식이 올바르지 않습니다."),
    NO_EXIST_COURSE("존재하지 않는 코스 입니다."),
    NO_EXIST_LEVEL("존재하지 않는 레벨 입니다."),
    NO_EXIST_MISSION("존재하지 않는 미션 정보 입니다."),
    NO_MAIN_OPTION("선택한 옵션이 존재하지 않습니다."),
    DISABLE_PAIR_MATCHING("페어 매칭이 불가능한 상태입니다."),
    FAILED_TO_MATCHING("매칭 시도 횟수가 초과하여 매칭에 실패했습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

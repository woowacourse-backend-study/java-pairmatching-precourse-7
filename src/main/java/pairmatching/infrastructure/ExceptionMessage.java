package pairmatching.infrastructure;

public enum ExceptionMessage {
    NO_EXIST_COURSE("존재하지 않는 코스 입니다."),
    NO_EXIST_LEVEL("존재하지 않는 레벨 입니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package pairmatching.view;

import java.util.HashSet;
import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.repository.CrewRepository;

public class OutputView {
    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public static void printPairMatch(List<String> randomCrews) {
        if (randomCrews.size() % 2 == 1) {
            for (int i = 0; i < randomCrews.size() - 3; i += 2) {
                System.out.println(randomCrews.get(i) + " : " + randomCrews.get(i + 1));
            }
        } else {
            for (int i = 0; i < randomCrews.size(); i += 2) {
                System.out.println(randomCrews.get(i) + " : " + randomCrews.get(i + 1));
            }
        }
    }

    public void printMainOption() {
        System.out.println("기능을 선택하세요");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
    }

    public static void printMatchStart() {
        System.out.println("\n#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

//    public void printStart() {
//        System.out.println(Message.OUTPUT_START.message);
}

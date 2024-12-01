package pairmatching.io;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.Course;
import pairmatching.Level;
import pairmatching.Mission;

public class InputView {

    public InputView() {

    }

    public String inputManFunction() {
        while (true) {
            System.out.println("""
                    기능을 선택하세요.
                    1. 페어 매칭
                    2. 페어 조회
                    3. 페어 초기화
                    Q. 종료""");
            String select = Console.readLine();
            System.out.println();
            if (select.equals("1") || select.equals("2") ||
                    select.equals("3") || select.equals("Q")) {
                return select;
            }
            System.out.println("[ERROR] 잘못된 입력입니다.");
        }
    }

    public MatchingSelect inputMatchingFunction() {
        while (true) {
            System.out.println("""
                    과정: 백엔드 | 프론트엔드
                    미션:
                      - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                      - 레벨2: 장바구니 | 결제 | 지하철노선도
                      - 레벨3:\s
                      - 레벨4: 성능개선 | 배포
                      - 레벨5:\s
                    ############################################
                    과정, 레벨, 미션을 선택하세요.
                    ex) 백엔드, 레벨1, 자동차경주""");
            String input = Console.readLine();
            System.out.println();
            String[] inputs = input.split(",");
            for (int i = 0; i < inputs.length; i++) {
                inputs[i] = inputs[i].trim();
            }
            if (!validateMatchingInput(inputs[0], inputs[1], inputs[2])) {
                continue;
            }
            return new MatchingSelect(inputs[0], inputs[1], inputs[2]);
        }

    }

    private boolean validateMatchingInput(String courName, String levelName, String missionName) {
        if (!Course.isExist(courName) || !Level.isExist(levelName) ||
                !Mission.isExist(missionName)) {
            System.out.println("[ERROR] 찾을 수 없는 값입니다.");
            return false;
        }
        return true;
    }


}

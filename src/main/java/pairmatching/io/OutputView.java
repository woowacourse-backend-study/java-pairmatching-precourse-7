package pairmatching.io;

import java.util.List;
import pairmatching.Pair;

public class OutputView {
    public void outputPair(List<Pair> resultPair) {
        if (resultPair.size() == 0) {
            System.out.println("[ERROR] 아직 매칭되지 않았습니다.");
            return;
        }

        System.out.println("페어 매칭 결과입니다.");
        resultPair.forEach(System.out::println);
        System.out.println();
    }

    public void outputReset() {
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }
}

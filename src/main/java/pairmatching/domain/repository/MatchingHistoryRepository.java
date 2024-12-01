package pairmatching.domain.repository;

import static java.util.spi.ToolProvider.findFirst;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingHistory;

public class MatchingHistoryRepository {
    private static List<MatchingHistory> matchingHistories = new ArrayList<>();

    public static void add(MatchingHistory history) {
        matchingHistories.add(history);
    }

    public static MatchingHistory findHistoryByLevel(String level) {
        return matchingHistories.stream()
                .filter(history -> history.getLevel().equals(level))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레벨입니다."));
    }

    public static void clear() {
        for (MatchingHistory history : matchingHistories) {
            MatchingHistory.clearStaticHistory();
        }
        matchingHistories = new ArrayList<>();

    }
}

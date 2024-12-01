package pairmatching.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pairmatching.domain.repository.CrewRepository;

public class MatchingHistory {
    private final Level level;
    private static final Map<Crew, Set<Crew>> matchingHistory = new HashMap<>();

    public MatchingHistory(Level level) {
        this.level = level;
    }

    public String getLevel() {
        return level.toString();
    }

    public static boolean validMatch(Crew crew1, Crew crew2) {
        Set<Crew> alreadyMet = matchingHistory.get(crew1);

        if (alreadyMet == null) {
            return true;
        }
        if (alreadyMet.contains(crew2)) {
            return false;
        }
        return true;
    }


    public static void clearStaticHistory() {
        matchingHistory.clear();
    }

    public static void addMatch(List<String> randomCrews) {
        if (randomCrews.size() % 2 == 1) {
            for (int i = 0; i < randomCrews.size() - 3; i += 2) {
                Crew crew1 = CrewRepository.findMenuByName(randomCrews.get(i));
                Crew crew2 = CrewRepository.findMenuByName(randomCrews.get(i + 1));

                matchingHistory.computeIfAbsent(crew1, k -> new HashSet<>()).add(crew2);
                matchingHistory.computeIfAbsent(crew2, k -> new HashSet<>()).add(crew1);
            }
        } else {
            for (int i = 0; i < randomCrews.size(); i += 2) {
                Crew crew1 = CrewRepository.findMenuByName(randomCrews.get(i));
                Crew crew2 = CrewRepository.findMenuByName(randomCrews.get(i + 1));

                matchingHistory.computeIfAbsent(crew1, k -> new HashSet<>()).add(crew2);
                matchingHistory.computeIfAbsent(crew2, k -> new HashSet<>()).add(crew1);
            }
        }
    }
}




package pairmatching.domain.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;

public class CrewRepository {

    private CrewRepository() {
    }

    private static final List<Crew> crews = new ArrayList<>();

    public static void add(Crew crew) {
        crews.add(crew);
    }

    public static Crew findMenuByName(String name) {
        return crews.stream()
                .filter(crew -> crew.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 크루"));
    }
}

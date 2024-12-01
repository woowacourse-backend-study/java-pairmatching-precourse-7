package pairmatching.repository;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Crew;

import java.util.ArrayList;
import java.util.List;

public class CrewRepository {
    private static List<Crew> crews = new ArrayList<>();

    public static void addCrew(Crew crew) {
        crews.add(crew);
    }

    public static List<Crew> findAllByCourse(Course course) {
        return crews.stream().filter(crew -> crew.getCourse() == course).toList();
    }

    public static void save(Crew crew) {
        crews.add(crew);
    }

    public static Crew findByCourseAndName(Course course, String name) {
        return crews.stream()
                .filter(crew -> crew.getCourse() == course && crew.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

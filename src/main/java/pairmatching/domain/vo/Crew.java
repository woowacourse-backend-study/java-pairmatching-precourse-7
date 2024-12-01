package pairmatching.domain.vo;

public class Crew {
    private Course course;
    private String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Crew) {
            return ((Crew) o).name.equals(name) && ((Crew) o).course == course;
        }
        return false;
    }
}


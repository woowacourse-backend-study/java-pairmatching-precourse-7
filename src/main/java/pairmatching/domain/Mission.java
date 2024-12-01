package pairmatching.domain;

public class Mission {
    private String name;

    private Mission(String name) {
        this.name = name;
    }

    public static Mission of(String name) {
        return new Mission(name);
    }

    public String getName() {
        return name;
    }
}

package pairmatching;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course findByName(String name) {
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return course;
            }
        }
        throw new RuntimeException("찾을 수 없는 값입니다");
    }

    public static boolean isExist(String name) {
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
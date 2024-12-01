package pairmatching.service.dto;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Mission;
import pairmatching.view.vo.CourseMissionInput;

public record PairMatchingRequest(Course course, Mission mission) {
    public static PairMatchingRequest of(CourseMissionInput input) {
        return new PairMatchingRequest(input.course(), input.mission());
    }
}

package pairmatching.domain;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Crew;

import java.util.List;

public class PairCrews {
    private static final String NAME_DELIMITER = " : ";
    private final List<Crew> crews;

    public PairCrews(List<Crew> crews) {
        validateCrews(crews);
        this.crews = crews;
    }

    private void validateCrews(List<Crew> crews) {
        Course course = crews.getFirst().getCourse();
        for (Crew crew : crews) {
            if (crew.getCourse() != course) {
                throw new IllegalStateException("서로 다른 과정에 속한 크루는 페어가 될 수 없습니다.");
            }
        }
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public String getPairString() {
        return String.join(NAME_DELIMITER, crews.stream().map(Crew::getName).toList());
    }

    /**
     * 다른 페어 매칭 결과와 중복되는지 확인
     * @param otherPair
     * @return
     */
    public boolean isDuplicate(PairCrews otherPair) {
        int matchCount = 0;
        List<Crew> otherCrews = otherPair.getCrews();
        for (Crew other : otherCrews) {
            if (crews.contains(other)) {
                matchCount++;
            }
        }
        return matchCount >= 2;
    }

    public boolean isDuplicate(List<Crew> otherCrews) {
        int matchCount = 0;
        for (Crew other : otherCrews) {
            if (crews.contains(other)) {
                matchCount++;
            }
        }
        return matchCount >= 2;
    }
}

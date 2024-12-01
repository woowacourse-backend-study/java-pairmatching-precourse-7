package pairmatching;

import java.util.List;

public class CrewFactory {
    private List<Crew> crews;

    private void addCrew(Crew newCrew) {
        for (Crew crew : crews) {
            if (crew.equals(newCrew)) {
                throw new RuntimeException("중복되는 이름입니다");
            }
        }
        crews.add(newCrew);
    }
}

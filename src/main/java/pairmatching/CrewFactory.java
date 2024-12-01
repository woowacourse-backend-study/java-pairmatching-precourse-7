package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class CrewFactory {
    private List<Crew> crews;

    public CrewFactory() {
        this.crews = new ArrayList<>();
    }

    public void addCrew(Crew newCrew) {
        for (Crew crew : crews) {
            if (crew.equals(newCrew)) {
                throw new RuntimeException("중복되는 이름입니다");
            }
        }
        crews.add(newCrew);
    }
}

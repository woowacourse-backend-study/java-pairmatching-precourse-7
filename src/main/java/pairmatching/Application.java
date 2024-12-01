package pairmatching;

import java.util.List;
import pairmatching.io.FileReader;
import pairmatching.io.InputView;
import pairmatching.io.OutputView;

public class Application {
    public static void main(String[] args) {
        CrewFactory crewFactory = new CrewFactory();
        FileReader fileReader = new FileReader(crewFactory);
        List<Crew> backendCrews = fileReader.readProduct("/backend-crew.md");
        List<Crew> frontendCrews = fileReader.readProduct("/frontend-crew.md");

        PairMachine pairMachine = new PairMachine(new InputView(), new OutputView(),
                backendCrews, frontendCrews);
        pairMachine.process();
    }
}

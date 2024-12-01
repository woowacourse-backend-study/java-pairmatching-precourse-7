package pairmatching.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import pairmatching.Application;
import pairmatching.Crew;
import pairmatching.CrewFactory;

public class FileReader {

    private CrewFactory crewFactory;

    public FileReader(CrewFactory crewFactory) {
        this.crewFactory = crewFactory;
    }

    public List<Crew> readProduct(String path) {
        List<Crew> crews = new ArrayList<>();
        try {
            InputStream backendInputStream = Application.class.getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(backendInputStream, StandardCharsets.UTF_8));
            String name;
            while ((name = br.readLine()) != null) {
                Crew crew = new Crew(name);
                crewFactory.addCrew(crew);
                crews.add(crew);
            }
        } catch (IOException e) {

        }
        return crews;
    }
}

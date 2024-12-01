package pairmatching.service;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Crew;
import pairmatching.repository.CrewRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InitializeService {
    private static final String FRONTEND_PATH = "src/main/resources/frontend-crew.md";
    private static final String BACKEND_PATH = "src/main/resources/backend-crew.md";

    public void loadAndSave() {
        try {
            loadFrontend();
            loadBackend();
        } catch (IOException e) {
            throw new RuntimeException("파일을 로드하는 과정에서 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private void loadFrontend() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FRONTEND_PATH));
        while (true) {
            String name = reader.readLine();
            if (name == null) break;
            CrewRepository.save(new Crew(Course.FRONTEND, name.trim()));
        }
    }

    private void loadBackend() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(BACKEND_PATH));
        while (true) {
            String name = reader.readLine();
            if (name == null) break;
            CrewRepository.save(new Crew(Course.BACKEND, name.trim()));
        }
    }
}

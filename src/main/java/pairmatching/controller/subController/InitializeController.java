package pairmatching.controller.subController;

import pairmatching.domain.repository.MatchingHistoryRepository;

public class InitializeController implements Controllable {

    @Override
    public void process() {
        MatchingHistoryRepository.clear();
    }
}

package pairmatching.controller;

import pairmatching.service.InitializeService;

public class InitializingController implements Controllable {

    @Override
    public void process() {
        InitializeService initializeService = new InitializeService();
        initializeService.loadAndSave();
    }
}

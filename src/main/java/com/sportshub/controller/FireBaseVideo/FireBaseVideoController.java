package com.sportshub.controller.FireBaseVideo;

import com.sportshub.service.FireBaseVideo.impl.FireBaseVideoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/fireBaseVideo/")
public class FireBaseVideoController {
    private final FireBaseVideoServiceImpl fireBaseVideoService;

    public FireBaseVideoController(FireBaseVideoServiceImpl fireBaseVideoService) {
        this.fireBaseVideoService = fireBaseVideoService;
    }
}

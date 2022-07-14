package com.sportshub.controller.FireBaseVideo;


import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;
import com.sportshub.service.FireBaseVideo.impl.FireBaseVideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/fireBaseVideo/")
public class FireBaseVideoController {
    private final FireBaseVideoServiceImpl fireBaseVideoService;

    @Autowired
    public FireBaseVideoController(FireBaseVideoServiceImpl fireBaseVideoService) {
        this.fireBaseVideoService = fireBaseVideoService;
    }

    @PostMapping(path = {"addNewVideo"})
    public ResponseEntity<String> regNewUser(@RequestBody FireBaseVideoEntity fireBaseVideoEntity) {
        return fireBaseVideoService.addNewVideo(fireBaseVideoEntity);
    }
}

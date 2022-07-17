package com.sportshub.controller.FireBaseVideo;


import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;
import com.sportshub.service.FireBaseVideo.impl.FireBaseVideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/fireBaseVideo/")
public class FireBaseVideoController {
    private final FireBaseVideoServiceImpl fireBaseVideoService;

    @Autowired
    public FireBaseVideoController(FireBaseVideoServiceImpl fireBaseVideoService) {
        this.fireBaseVideoService = fireBaseVideoService;
    }

    @PostMapping(path = {"addNewVideo"})
    public ResponseEntity<String> addNewVideo(@RequestBody FireBaseVideoEntity fireBaseVideoEntity) {
        return fireBaseVideoService.addNewVideo(fireBaseVideoEntity);
    }

    @GetMapping(path = {"getVideos{params}" , "getVideos"})
    public ResponseEntity<List<FireBaseVideoEntity>> getVideos(@RequestParam(required = false) Integer params){
        return fireBaseVideoService.getVideo(params);
    }


    @PatchMapping(path = {"changeVisibility{id}{val}"})
    public ResponseEntity<String>changeVisibility(@RequestParam() long id,@RequestParam() Boolean val){
        return fireBaseVideoService.changeVisibility(id,val);
    }

    @PatchMapping(path = {"updateDescription{id}{val}"})
    public ResponseEntity<String>updateDescription(@RequestParam() long id,@RequestParam() String val){
        return fireBaseVideoService.updateDescription(id,val);
    }

    @DeleteMapping(path = {"deleteById{id}"})
    public ResponseEntity<String>deleteById(@RequestParam() long id){
        return fireBaseVideoService.deleteById(id);
    }


}

package com.sportshub.service.FireBaseVideo;

import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;
import org.springframework.http.ResponseEntity;

public interface FireBaseVideoService {

    ResponseEntity<String> addNewVideo(FireBaseVideoEntity fireBaseVideoEntity);
}

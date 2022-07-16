package com.sportshub.service.FireBaseVideo;

import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FireBaseVideoService {

    ResponseEntity<String> addNewVideo(FireBaseVideoEntity fireBaseVideoEntity);

    ResponseEntity<List<FireBaseVideoEntity>> getVideo(Integer params);
}

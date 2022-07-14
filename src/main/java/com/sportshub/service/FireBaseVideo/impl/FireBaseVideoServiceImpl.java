package com.sportshub.service.FireBaseVideo.impl;

import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;
import com.sportshub.repository.FireBaseVideo.FireBaseVideoRepository;
import com.sportshub.service.FireBaseVideo.FireBaseVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class FireBaseVideoServiceImpl implements FireBaseVideoService {

    private final FireBaseVideoRepository fireBaseVideoRepository;

    public FireBaseVideoServiceImpl(FireBaseVideoRepository fireBaseVideoRepository) {
        this.fireBaseVideoRepository = fireBaseVideoRepository;
    }

    @Override
    public ResponseEntity<String> addNewVideo(FireBaseVideoEntity fireBaseVideoEntity){
        fireBaseVideoRepository.save(fireBaseVideoEntity);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/fireBaseVideo/addNewVideo").toUriString());
        return ResponseEntity.created(uri).build();
    }
}

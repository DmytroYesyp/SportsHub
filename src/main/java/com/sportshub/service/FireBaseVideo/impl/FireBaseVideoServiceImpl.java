package com.sportshub.service.FireBaseVideo.impl;

import com.sportshub.repository.FireBaseVideo.FireBaseVideoRepository;
import org.springframework.stereotype.Service;

@Service
public class FireBaseVideoServiceImpl {

    private final FireBaseVideoRepository fireBaseVideoRepository;

    public FireBaseVideoServiceImpl(FireBaseVideoRepository fireBaseVideoRepository) {
        this.fireBaseVideoRepository = fireBaseVideoRepository;
    }
}

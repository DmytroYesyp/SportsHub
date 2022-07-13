package com.sportshub.repository.FireBaseVideo;

import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("FireBaseVideoRepository")
public interface FireBaseVideoRepository extends JpaRepository<FireBaseVideoEntity, Long> {
}

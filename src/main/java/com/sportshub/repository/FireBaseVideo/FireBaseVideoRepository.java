package com.sportshub.repository.FireBaseVideo;

import com.sportshub.entity.FireBaseVideo.FireBaseVideoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("FireBaseVideoRepository")
public interface FireBaseVideoRepository extends JpaRepository<FireBaseVideoEntity, Long> {

    List<FireBaseVideoEntity> IsVisible(Boolean isVisible);

}

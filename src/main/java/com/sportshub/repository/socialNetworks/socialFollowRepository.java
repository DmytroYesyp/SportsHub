package com.sportshub.repository.socialNetworks;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("socialFollowRepository")
public interface socialFollowRepository extends JpaRepository<socialFollow, Long> {
    Optional<socialFollow> findShareByPictogram(String pictogram);
}

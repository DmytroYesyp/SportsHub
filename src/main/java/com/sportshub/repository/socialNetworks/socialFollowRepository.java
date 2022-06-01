package com.sportshub.repository.socialNetworks;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socialFollowRepository")
public interface socialFollowRepository extends JpaRepository<socialFollow, Long> {
}

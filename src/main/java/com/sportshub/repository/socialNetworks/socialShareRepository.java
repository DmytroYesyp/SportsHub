package com.sportshub.repository.socialNetworks;

import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("socialShareRepository")
public interface socialShareRepository extends JpaRepository<socialShare, Long> {

    Optional<socialShare> findShareByPictogram(String pictogram);

}

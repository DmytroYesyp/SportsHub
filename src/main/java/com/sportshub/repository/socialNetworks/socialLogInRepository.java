package com.sportshub.repository.socialNetworks;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import com.sportshub.entity.socialNetworks.socialLogIn.socialLogIn;
import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("socialLogInRepository")
public interface socialLogInRepository extends JpaRepository<socialLogIn, Long> {
    Optional<socialLogIn> findLogInByPictogram(String pictogram);
}

package com.sportshub.repository.socialNetworks;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import com.sportshub.entity.socialNetworks.socialLogIn.socialLogIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socialLogInRepository")
public interface socialLogInRepository extends JpaRepository<socialLogIn, Long> {
}

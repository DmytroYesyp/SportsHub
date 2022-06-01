package com.sportshub.repository.socialNetworks;

import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socialShareRepository")
public interface socialShareRepository extends JpaRepository<socialShare, Long> {
}

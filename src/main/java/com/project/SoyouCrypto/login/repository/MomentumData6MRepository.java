package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.MomentumData6M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MomentumData6MRepository extends JpaRepository<MomentumData6M, Integer> {
    Optional<MomentumData6M> findByTime(String time);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table momentum_data6m")
    void truncateMomentumData6M();


}
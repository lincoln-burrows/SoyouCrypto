package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.MomentumData3M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MomentumData3MRepository extends JpaRepository<MomentumData3M, Integer> {
    Optional<MomentumData3M> findByTime(String time);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table momentum_data3m")
    void truncateMomentumData3M();


}
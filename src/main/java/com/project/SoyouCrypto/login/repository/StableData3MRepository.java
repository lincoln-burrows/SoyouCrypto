package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableData3M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StableData3MRepository extends JpaRepository<StableData3M, Integer> {
    Optional<StableData3M> findByDatetime(String time);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table stable_data3m")
    void truncateStableData3M();


}
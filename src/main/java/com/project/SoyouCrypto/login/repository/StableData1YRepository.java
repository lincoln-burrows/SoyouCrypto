package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableData1Y;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StableData1YRepository extends JpaRepository<StableData1Y, Integer> {
    Optional<StableData1Y> findByDatetime(String time);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table stable_data1y")
    void truncateStableData1Y();


}
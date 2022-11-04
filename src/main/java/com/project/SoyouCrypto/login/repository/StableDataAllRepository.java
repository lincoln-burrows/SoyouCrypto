package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableDataAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StableDataAllRepository extends JpaRepository<StableDataAll, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table stable_data_all")
    void truncateStableDataAll();


}
package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.MomentumDataAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MomentumDataAllRepository extends JpaRepository<MomentumDataAll, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table momentum_data_all")
    void truncateMomentumDataAll();


}
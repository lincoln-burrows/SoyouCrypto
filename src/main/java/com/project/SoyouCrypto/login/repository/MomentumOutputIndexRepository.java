package com.project.SoyouCrypto.login.repository;




import com.project.SoyouCrypto.login.model.MomentumOutputIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MomentumOutputIndexRepository extends JpaRepository<MomentumOutputIndex, Integer> {
    Optional<MomentumOutputIndex> findByIndexType(String indexType);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table momentum_output_index")
    void truncateMomentumOutputIndex();


}
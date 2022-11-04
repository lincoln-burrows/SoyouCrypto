package com.project.SoyouCrypto.login.repository;





import com.project.SoyouCrypto.login.model.StableOutputIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StableOutputIndexRepository extends JpaRepository<StableOutputIndex, Integer> {
    Optional<StableOutputIndex> findByIndexType(String indexType);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "truncate table stable_output_index")
    void truncateStableOutputIndex();

}
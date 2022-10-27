package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.OutputIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutputIndexRepository extends JpaRepository<OutputIndex, Integer> {
    Optional<OutputIndex> findByIndexType(String indexType);


}
package com.testone.repository;

import com.testone.model.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankInformationRepository extends JpaRepository<BankInformation, UUID> {
    List<BankInformation> findByUserId(UUID userId);
    Page<BankInformation> findByUserId(UUID userId, Pageable pageable);
}

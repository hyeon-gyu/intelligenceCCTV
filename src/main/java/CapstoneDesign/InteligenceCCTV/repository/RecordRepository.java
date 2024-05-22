package CapstoneDesign.InteligenceCCTV.repository;

import CapstoneDesign.InteligenceCCTV.entity.CctvRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RecordRepository extends JpaRepository<CctvRecord, Long> {

/*    boolean existsByDangerAndCreatedAtAfter(String danger, LocalDateTime createdAt);*/
}

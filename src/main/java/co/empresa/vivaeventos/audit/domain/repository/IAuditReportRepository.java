package co.empresa.vivaeventos.audit.domain.repository;

import co.empresa.vivaeventos.audit.domain.model.AuditReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IAuditReportRepository extends JpaRepository<AuditReport, UUID> {

    List<AuditReport> findByGeneratedByOrderByGeneratedAtDesc(UUID generatedBy);
}

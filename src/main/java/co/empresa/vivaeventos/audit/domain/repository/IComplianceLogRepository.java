package co.empresa.vivaeventos.audit.domain.repository;

import co.empresa.vivaeventos.audit.domain.model.ComplianceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IComplianceLogRepository extends JpaRepository<ComplianceLog, UUID>, JpaSpecificationExecutor<ComplianceLog> {
}

package co.empresa.vivaeventos.audit.domain.repository;

import co.empresa.vivaeventos.audit.domain.model.SensitiveDataAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ISensitiveDataAccessRepository extends JpaRepository<SensitiveDataAccess, UUID> {

    List<SensitiveDataAccess> findByUserIdOrderByCreatedAtDesc(UUID userId);

    List<SensitiveDataAccess> findByDataTypeOrderByCreatedAtDesc(String dataType);
}

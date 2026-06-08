package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.Dto.ComplianceLogRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.ComplianceLogResponse;

import java.util.List;
import java.util.UUID;

public interface IComplianceLogService {

    ComplianceLogResponse logEvent(ComplianceLogRequest request);

    ComplianceLogResponse getById(UUID id);

    List<ComplianceLogResponse> getAll();
}

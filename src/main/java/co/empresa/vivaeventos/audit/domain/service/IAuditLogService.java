package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.AuditLog;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogFilterRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogResponse;
import co.empresa.vivaeventos.audit.domain.model.Dto.PagedResponse;

import java.util.UUID;

public interface IAuditLogService {

    AuditLogResponse logEvent(AuditLogRequest request);

    AuditLogResponse getLogById(UUID id);

    PagedResponse<AuditLogResponse> getLogs(AuditLogFilterRequest filter);
}

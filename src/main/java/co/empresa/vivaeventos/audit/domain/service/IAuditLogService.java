package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.AuditLog;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogFilterRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogResponse;
import co.empresa.vivaeventos.audit.domain.model.dto.PagedResponse;

import java.util.UUID;

public interface IAuditLogService {

    AuditLogResponse logEvent(AuditLogRequest request);

    AuditLogResponse getLogById(UUID id);

    PagedResponse<AuditLogResponse> getLogs(AuditLogFilterRequest filter);
}

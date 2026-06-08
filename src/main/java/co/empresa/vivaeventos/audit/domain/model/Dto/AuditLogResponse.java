package co.empresa.vivaeventos.audit.domain.model.Dto;

import co.empresa.vivaeventos.audit.domain.model.AuditLog;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuditLogResponse {

    private UUID id;
    private String serviceName;
    private UUID userId;
    private String userRole;
    private String action;
    private String entityType;
    private UUID entityId;
    private String oldValues;
    private String newValues;
    private String ipAddress;
    private String userAgent;
    private UUID requestId;
    private UUID correlationId;
    private String status;
    private String errorMessage;
    private Integer durationMs;
    private LocalDateTime createdAt;

    public static AuditLogResponse fromEntity(AuditLog log) {
        AuditLogResponse r = new AuditLogResponse();
        r.setId(log.getId());
        r.setServiceName(log.getServiceName());
        r.setUserId(log.getUserId());
        r.setUserRole(log.getUserRole());
        r.setAction(log.getAction());
        r.setEntityType(log.getEntityType());
        r.setEntityId(log.getEntityId());
        r.setOldValues(log.getOldValues());
        r.setNewValues(log.getNewValues());
        r.setIpAddress(log.getIpAddress());
        r.setUserAgent(log.getUserAgent());
        r.setRequestId(log.getRequestId());
        r.setCorrelationId(log.getCorrelationId());
        r.setStatus(log.getStatus());
        r.setErrorMessage(log.getErrorMessage());
        r.setDurationMs(log.getDurationMs());
        r.setCreatedAt(log.getCreatedAt());
        return r;
    }
}

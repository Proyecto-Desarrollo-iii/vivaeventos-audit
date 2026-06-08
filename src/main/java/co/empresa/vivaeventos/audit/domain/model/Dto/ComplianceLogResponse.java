package co.empresa.vivaeventos.audit.domain.model.Dto;

import co.empresa.vivaeventos.audit.domain.model.ComplianceLog;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComplianceLogResponse {

    private UUID id;
    private String eventType;
    private UUID userId;
    private String data;
    private LocalDateTime retentionUntil;
    private LocalDateTime createdAt;

    public static ComplianceLogResponse fromEntity(ComplianceLog log) {
        ComplianceLogResponse r = new ComplianceLogResponse();
        r.setId(log.getId());
        r.setEventType(log.getEventType());
        r.setUserId(log.getUserId());
        r.setData(log.getData());
        r.setRetentionUntil(log.getRetentionUntil());
        r.setCreatedAt(log.getCreatedAt());
        return r;
    }
}

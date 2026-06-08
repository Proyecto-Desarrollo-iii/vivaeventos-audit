package co.empresa.vivaeventos.audit.domain.model.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuditLogFilterRequest {

    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private UUID userId;
    private String action;
    private String serviceName;
    private String entityType;
    private String status;
    private int page = 0;
    private int size = 20;
}

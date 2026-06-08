package co.empresa.vivaeventos.audit.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class AuditLogRequest {

    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(max = 100)
    private String serviceName;

    private UUID userId;

    @Size(max = 50)
    private String userRole;

    @NotBlank(message = "La accion es obligatoria")
    @Size(max = 100)
    private String action;

    @Size(max = 100)
    private String entityType;

    private UUID entityId;

    private String oldValues;

    private String newValues;

    @Size(max = 50)
    private String ipAddress;

    private String userAgent;

    private UUID requestId;

    private UUID correlationId;

    @Size(max = 50)
    private String status;

    @Size(max = 500)
    private String errorMessage;

    private Integer durationMs;
}

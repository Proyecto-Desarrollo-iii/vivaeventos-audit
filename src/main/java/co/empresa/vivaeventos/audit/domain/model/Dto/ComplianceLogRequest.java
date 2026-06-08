package co.empresa.vivaeventos.audit.domain.model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComplianceLogRequest {

    @NotBlank
    @Size(max = 100)
    private String eventType;

    private UUID userId;

    @NotBlank
    private String data;

    private LocalDateTime retentionUntil;
}

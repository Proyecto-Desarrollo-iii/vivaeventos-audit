package co.empresa.vivaeventos.audit.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class AuditReportRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;

    private String filters;

    @NotNull
    private UUID generatedBy;
}

package co.empresa.vivaeventos.audit.domain.model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class SensitiveDataAccessRequest {

    @NotNull
    private UUID userId;

    @NotBlank
    @Size(max = 100)
    private String dataType;

    private UUID entityId;

    @Size(max = 255)
    private String accessReason;

    @Size(max = 50)
    private String ipAddress;
}

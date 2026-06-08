package co.empresa.vivaeventos.audit.domain.model.Dto;

import co.empresa.vivaeventos.audit.domain.model.SensitiveDataAccess;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SensitiveDataAccessResponse {

    private UUID id;
    private UUID userId;
    private String dataType;
    private UUID entityId;
    private String accessReason;
    private String ipAddress;
    private LocalDateTime createdAt;

    public static SensitiveDataAccessResponse fromEntity(SensitiveDataAccess access) {
        SensitiveDataAccessResponse r = new SensitiveDataAccessResponse();
        r.setId(access.getId());
        r.setUserId(access.getUserId());
        r.setDataType(access.getDataType());
        r.setEntityId(access.getEntityId());
        r.setAccessReason(access.getAccessReason());
        r.setIpAddress(access.getIpAddress());
        r.setCreatedAt(access.getCreatedAt());
        return r;
    }
}

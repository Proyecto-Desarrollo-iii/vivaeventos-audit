package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.Dto.SensitiveDataAccessRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.SensitiveDataAccessResponse;

import java.util.List;
import java.util.UUID;

public interface ISensitiveDataAccessService {

    SensitiveDataAccessResponse registerAccess(SensitiveDataAccessRequest request);

    SensitiveDataAccessResponse getById(UUID id);

    List<SensitiveDataAccessResponse> getByUser(UUID userId);

    List<SensitiveDataAccessResponse> getByDataType(String dataType);

    List<SensitiveDataAccessResponse> getAll();
}

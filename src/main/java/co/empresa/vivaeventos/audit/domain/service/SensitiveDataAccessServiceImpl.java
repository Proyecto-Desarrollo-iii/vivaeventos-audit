package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.SensitiveDataAccess;
import co.empresa.vivaeventos.audit.domain.model.Dto.SensitiveDataAccessRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.SensitiveDataAccessResponse;
import co.empresa.vivaeventos.audit.domain.repository.ISensitiveDataAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensitiveDataAccessServiceImpl implements ISensitiveDataAccessService {

    private final ISensitiveDataAccessRepository repository;

    @Override
    @Transactional
    public SensitiveDataAccessResponse registerAccess(SensitiveDataAccessRequest request) {
        SensitiveDataAccess access = new SensitiveDataAccess();
        access.setUserId(request.getUserId());
        access.setDataType(request.getDataType());
        access.setEntityId(request.getEntityId());
        access.setAccessReason(request.getAccessReason());
        access.setIpAddress(request.getIpAddress());
        access = repository.save(access);
        return SensitiveDataAccessResponse.fromEntity(access);
    }

    @Override
    @Transactional(readOnly = true)
    public SensitiveDataAccessResponse getById(java.util.UUID id) {
        SensitiveDataAccess access = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SensitiveDataAccess no encontrado: " + id));
        return SensitiveDataAccessResponse.fromEntity(access);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensitiveDataAccessResponse> getByUser(java.util.UUID userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(SensitiveDataAccessResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensitiveDataAccessResponse> getByDataType(String dataType) {
        return repository.findByDataTypeOrderByCreatedAtDesc(dataType).stream()
                .map(SensitiveDataAccessResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensitiveDataAccessResponse> getAll() {
        return repository.findAll().stream()
                .map(SensitiveDataAccessResponse::fromEntity)
                .toList();
    }
}

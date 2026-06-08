package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.ComplianceLog;
import co.empresa.vivaeventos.audit.domain.model.Dto.ComplianceLogRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.ComplianceLogResponse;
import co.empresa.vivaeventos.audit.domain.repository.IComplianceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplianceLogServiceImpl implements IComplianceLogService {

    private final IComplianceLogRepository repository;

    @Override
    @Transactional
    public ComplianceLogResponse logEvent(ComplianceLogRequest request) {
        ComplianceLog log = new ComplianceLog();
        log.setEventType(request.getEventType());
        log.setUserId(request.getUserId());
        log.setData(request.getData());
        log.setRetentionUntil(request.getRetentionUntil());
        log = repository.save(log);
        return ComplianceLogResponse.fromEntity(log);
    }

    @Override
    @Transactional(readOnly = true)
    public ComplianceLogResponse getById(java.util.UUID id) {
        ComplianceLog log = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ComplianceLog no encontrado: " + id));
        return ComplianceLogResponse.fromEntity(log);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplianceLogResponse> getAll() {
        return repository.findAll().stream()
                .map(ComplianceLogResponse::fromEntity)
                .toList();
    }
}

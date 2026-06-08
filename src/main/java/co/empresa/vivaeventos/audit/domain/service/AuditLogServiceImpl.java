package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.AuditLog;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogFilterRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogResponse;
import co.empresa.vivaeventos.audit.domain.model.Dto.PagedResponse;
import co.empresa.vivaeventos.audit.domain.repository.IAuditLogRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements IAuditLogService {

    private final IAuditLogRepository repository;

    @Override
    @Transactional
    public AuditLogResponse logEvent(AuditLogRequest request) {
        AuditLog log = new AuditLog();
        log.setServiceName(request.getServiceName());
        log.setUserId(request.getUserId());
        log.setUserRole(request.getUserRole());
        log.setAction(request.getAction());
        log.setEntityType(request.getEntityType());
        log.setEntityId(request.getEntityId());
        log.setOldValues(request.getOldValues());
        log.setNewValues(request.getNewValues());
        log.setIpAddress(request.getIpAddress());
        log.setUserAgent(request.getUserAgent());
        log.setRequestId(request.getRequestId());
        log.setCorrelationId(request.getCorrelationId());
        log.setStatus(request.getStatus());
        log.setErrorMessage(request.getErrorMessage());
        log.setDurationMs(request.getDurationMs());
        log = repository.save(log);
        return AuditLogResponse.fromEntity(log);
    }

    @Override
    @Transactional(readOnly = true)
    public AuditLogResponse getLogById(java.util.UUID id) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AuditLog no encontrado: " + id));
        return AuditLogResponse.fromEntity(log);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<AuditLogResponse> getLogs(AuditLogFilterRequest filter) {
        Specification<AuditLog> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getFechaDesde() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.getFechaDesde()));
            }
            if (filter.getFechaHasta() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filter.getFechaHasta()));
            }
            if (filter.getUserId() != null) {
                predicates.add(cb.equal(root.get("userId"), filter.getUserId()));
            }
            if (filter.getAction() != null && !filter.getAction().isBlank()) {
                predicates.add(cb.equal(root.get("action"), filter.getAction()));
            }
            if (filter.getServiceName() != null && !filter.getServiceName().isBlank()) {
                predicates.add(cb.equal(root.get("serviceName"), filter.getServiceName()));
            }
            if (filter.getEntityType() != null && !filter.getEntityType().isBlank()) {
                predicates.add(cb.equal(root.get("entityType"), filter.getEntityType()));
            }
            if (filter.getStatus() != null && !filter.getStatus().isBlank()) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        PageRequest pageRequest = PageRequest.of(
                filter.getPage(), filter.getSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<AuditLog> page = repository.findAll(spec, pageRequest);
        List<AuditLogResponse> content = page.getContent().stream()
                .map(AuditLogResponse::fromEntity)
                .toList();

        return new PagedResponse<>(
                content, page.getTotalElements(),
                page.getTotalPages(), page.getNumber(), page.getSize()
        );
    }
}

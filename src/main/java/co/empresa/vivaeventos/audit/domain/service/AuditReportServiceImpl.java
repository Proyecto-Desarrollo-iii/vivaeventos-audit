package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.AuditReport;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditReportRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditReportResponse;
import co.empresa.vivaeventos.audit.domain.repository.IAuditReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditReportServiceImpl implements IAuditReportService {

    private final IAuditReportRepository repository;

    @Override
    @Transactional
    public AuditReportResponse createReport(AuditReportRequest request) {
        AuditReport report = new AuditReport();
        report.setName(request.getName());
        report.setDescription(request.getDescription());
        report.setFilters(request.getFilters());
        report.setGeneratedBy(request.getGeneratedBy());
        report = repository.save(report);
        return AuditReportResponse.fromEntity(report);
    }

    @Override
    @Transactional(readOnly = true)
    public AuditReportResponse getById(java.util.UUID id) {
        AuditReport report = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AuditReport no encontrado: " + id));
        return AuditReportResponse.fromEntity(report);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditReportResponse> getByUser(java.util.UUID userId) {
        return repository.findByGeneratedByOrderByGeneratedAtDesc(userId).stream()
                .map(AuditReportResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditReportResponse> getAll() {
        return repository.findAll().stream()
                .map(AuditReportResponse::fromEntity)
                .toList();
    }
}

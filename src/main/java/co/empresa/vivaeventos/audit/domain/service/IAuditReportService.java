package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.dto.AuditReportRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditReportResponse;

import java.util.List;
import java.util.UUID;

public interface IAuditReportService {

    AuditReportResponse createReport(AuditReportRequest request);

    AuditReportResponse getById(UUID id);

    List<AuditReportResponse> getByUser(UUID userId);

    List<AuditReportResponse> getAll();
}

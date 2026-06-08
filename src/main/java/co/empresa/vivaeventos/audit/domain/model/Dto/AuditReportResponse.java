package co.empresa.vivaeventos.audit.domain.model.Dto;

import co.empresa.vivaeventos.audit.domain.model.AuditReport;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuditReportResponse {

    private UUID id;
    private String name;
    private String description;
    private String filters;
    private UUID generatedBy;
    private LocalDateTime generatedAt;

    public static AuditReportResponse fromEntity(AuditReport report) {
        AuditReportResponse r = new AuditReportResponse();
        r.setId(report.getId());
        r.setName(report.getName());
        r.setDescription(report.getDescription());
        r.setFilters(report.getFilters());
        r.setGeneratedBy(report.getGeneratedBy());
        r.setGeneratedAt(report.getGeneratedAt());
        return r;
    }
}

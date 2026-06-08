package co.empresa.vivaeventos.audit.delivery.rest;

import co.empresa.vivaeventos.audit.domain.model.Dto.AuditReportRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditReportResponse;
import co.empresa.vivaeventos.audit.domain.service.IAuditReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/audit/reports")
@RequiredArgsConstructor
public class AuditReportController {

    private final IAuditReportService auditReportService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createReport(@Valid @RequestBody AuditReportRequest request) {
        AuditReportResponse response = auditReportService.createReport(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("report", response));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseEntity.ok(Map.of("reports", auditReportService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        AuditReportResponse response = auditReportService.getById(id);
        return ResponseEntity.ok(Map.of("report", response));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(Map.of("reports", auditReportService.getByUser(userId)));
    }
}

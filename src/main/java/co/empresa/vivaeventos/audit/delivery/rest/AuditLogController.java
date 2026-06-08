package co.empresa.vivaeventos.audit.delivery.rest;

import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogFilterRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.AuditLogResponse;
import co.empresa.vivaeventos.audit.domain.model.Dto.PagedResponse;
import co.empresa.vivaeventos.audit.domain.service.IAuditLogService;
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
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final IAuditLogService auditLogService;

    @PostMapping("/log")
    public ResponseEntity<Map<String, Object>> logEvent(@Valid @RequestBody AuditLogRequest request) {
        AuditLogResponse response = auditLogService.logEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("log", response));
    }

    @GetMapping("/logs")
    public ResponseEntity<Map<String, Object>> getLogs(AuditLogFilterRequest filter) {
        PagedResponse<AuditLogResponse> logs = auditLogService.getLogs(filter);
        return ResponseEntity.ok(Map.of(
                "logs", logs.getContent(),
                "total", logs.getTotalElements(),
                "page", logs.getPage(),
                "size", logs.getSize(),
                "totalPages", logs.getTotalPages()
        ));
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<Map<String, Object>> getLogById(@PathVariable UUID id) {
        AuditLogResponse response = auditLogService.getLogById(id);
        return ResponseEntity.ok(Map.of("log", response));
    }
}

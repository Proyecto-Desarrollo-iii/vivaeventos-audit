package co.empresa.vivaeventos.audit.delivery.rest;

import co.empresa.vivaeventos.audit.domain.model.Dto.ComplianceLogRequest;
import co.empresa.vivaeventos.audit.domain.model.Dto.ComplianceLogResponse;
import co.empresa.vivaeventos.audit.domain.service.IComplianceLogService;
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
@RequestMapping("/api/v1/audit/compliance")
@RequiredArgsConstructor
public class ComplianceLogController {

    private final IComplianceLogService complianceLogService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> logEvent(@Valid @RequestBody ComplianceLogRequest request) {
        ComplianceLogResponse response = complianceLogService.logEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("log", response));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseEntity.ok(Map.of("logs", complianceLogService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        ComplianceLogResponse response = complianceLogService.getById(id);
        return ResponseEntity.ok(Map.of("log", response));
    }
}

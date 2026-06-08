package co.empresa.vivaeventos.audit.delivery.rest;

import co.empresa.vivaeventos.audit.domain.model.dto.SensitiveDataAccessRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.SensitiveDataAccessResponse;
import co.empresa.vivaeventos.audit.domain.service.ISensitiveDataAccessService;
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
@RequestMapping("/api/v1/audit/sensitive-access")
@RequiredArgsConstructor
public class SensitiveDataAccessController {

    private final ISensitiveDataAccessService sensitiveDataAccessService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerAccess(@Valid @RequestBody SensitiveDataAccessRequest request) {
        SensitiveDataAccessResponse response = sensitiveDataAccessService.registerAccess(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("access", response));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseEntity.ok(Map.of("accesses", sensitiveDataAccessService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        SensitiveDataAccessResponse response = sensitiveDataAccessService.getById(id);
        return ResponseEntity.ok(Map.of("access", response));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(Map.of("accesses", sensitiveDataAccessService.getByUser(userId)));
    }

    @GetMapping("/data-type/{dataType}")
    public ResponseEntity<Map<String, Object>> getByDataType(@PathVariable String dataType) {
        return ResponseEntity.ok(Map.of("accesses", sensitiveDataAccessService.getByDataType(dataType)));
    }
}

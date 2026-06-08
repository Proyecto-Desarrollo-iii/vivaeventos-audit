package co.empresa.vivaeventos.audit.domain.service;

import co.empresa.vivaeventos.audit.domain.model.AuditLog;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogFilterRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogResponse;
import co.empresa.vivaeventos.audit.domain.model.dto.PagedResponse;
import co.empresa.vivaeventos.audit.domain.repository.IAuditLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceImplTest {

    @Mock
    private IAuditLogRepository repository;

    @InjectMocks
    private AuditLogServiceImpl service;

    @Test
    void logEvent_ShouldSaveAndReturnResponse() {
        AuditLogRequest request = new AuditLogRequest();
        request.setServiceName("auth");
        request.setAction("LOGIN");
        request.setUserId(UUID.randomUUID());
        request.setIpAddress("192.168.1.1");

        AuditLog savedLog = new AuditLog();
        savedLog.setId(UUID.randomUUID());
        savedLog.setServiceName("auth");
        savedLog.setAction("LOGIN");
        savedLog.setUserId(request.getUserId());
        savedLog.setIpAddress("192.168.1.1");
        savedLog.setCreatedAt(LocalDateTime.now());

        when(repository.save(any(AuditLog.class))).thenReturn(savedLog);

        AuditLogResponse response = service.logEvent(request);

        assertNotNull(response);
        assertEquals("auth", response.getServiceName());
        assertEquals("LOGIN", response.getAction());
        assertEquals("192.168.1.1", response.getIpAddress());

        ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);
        verify(repository).save(captor.capture());
        assertEquals("auth", captor.getValue().getServiceName());
    }

    @Test
    void getLogById_ShouldReturnResponse() {
        UUID id = UUID.randomUUID();
        AuditLog log = new AuditLog();
        log.setId(id);
        log.setServiceName("events");
        log.setAction("CREAR_EVENTO");

        when(repository.findById(id)).thenReturn(Optional.of(log));

        AuditLogResponse response = service.getLogById(id);

        assertEquals(id, response.getId());
        assertEquals("events", response.getServiceName());
    }

    @Test
    void getLogById_ShouldThrowWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.getLogById(id));
    }

    @Test
    void getLogs_ShouldApplyFiltersAndReturnPagedResponse() {
        AuditLog log = new AuditLog();
        log.setId(UUID.randomUUID());
        log.setServiceName("auth");
        log.setAction("LOGIN");
        log.setCreatedAt(LocalDateTime.now());

        Page<AuditLog> page = new PageImpl<>(List.of(log));
        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        AuditLogFilterRequest filter = new AuditLogFilterRequest();
        filter.setServiceName("auth");
        filter.setAction("LOGIN");
        filter.setPage(0);
        filter.setSize(20);

        PagedResponse<AuditLogResponse> result = service.getLogs(filter);

        assertEquals(1, result.getTotalElements());
        assertEquals("auth", result.getContent().getFirst().getServiceName());
    }
}

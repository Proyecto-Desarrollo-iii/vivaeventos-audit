package co.empresa.vivaeventos.audit.delivery.rest;

import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogFilterRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogRequest;
import co.empresa.vivaeventos.audit.domain.model.dto.AuditLogResponse;
import co.empresa.vivaeventos.audit.domain.model.dto.PagedResponse;
import co.empresa.vivaeventos.audit.domain.service.IAuditLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuditLogController.class)
@WithMockUser
class AuditLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IAuditLogService auditLogService;

    @Test
    void logEvent_ShouldReturn201() throws Exception {
        AuditLogRequest request = new AuditLogRequest();
        request.setServiceName("auth");
        request.setAction("LOGIN");
        request.setUserId(UUID.randomUUID());

        AuditLogResponse response = new AuditLogResponse();
        response.setId(UUID.randomUUID());
        response.setServiceName("auth");
        response.setAction("LOGIN");
        response.setCreatedAt(LocalDateTime.now());

        when(auditLogService.logEvent(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/audit/log")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.log.serviceName").value("auth"))
                .andExpect(jsonPath("$.log.action").value("LOGIN"));
    }

    @Test
    void getLogById_ShouldReturn200() throws Exception {
        UUID id = UUID.randomUUID();
        AuditLogResponse response = new AuditLogResponse();
        response.setId(id);
        response.setServiceName("events");
        response.setAction("CREAR_EVENTO");
        response.setCreatedAt(LocalDateTime.now());

        when(auditLogService.getLogById(id)).thenReturn(response);

        mockMvc.perform(get("/api/v1/audit/logs/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.log.id").value(id.toString()));
    }

    @Test
    void getLogs_ShouldReturn200() throws Exception {
        AuditLogResponse log1 = new AuditLogResponse();
        log1.setId(UUID.randomUUID());
        log1.setServiceName("auth");
        log1.setAction("LOGIN");

        PagedResponse<AuditLogResponse> paged = new PagedResponse<>(List.of(log1), 1, 1, 0, 20);

        when(auditLogService.getLogs(any(AuditLogFilterRequest.class))).thenReturn(paged);

        mockMvc.perform(get("/api/v1/audit/logs")
                        .param("serviceName", "auth")
                        .param("action", "LOGIN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logs[0].action").value("LOGIN"))
                .andExpect(jsonPath("$.total").value(1));
    }
}

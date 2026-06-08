package co.empresa.vivaeventos.audit.domain.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int page;
    private int size;
}

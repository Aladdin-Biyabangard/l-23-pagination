package az.ingress.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPage<T> {
    private List<T> data;
    private int lastPageNumber;
    private boolean hasNextPage;
}

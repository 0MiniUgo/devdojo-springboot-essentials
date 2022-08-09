package academy.devdojo.springboot.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AnimePutRequestBody {
    @NotBlank(message = "The anime id cannot be null or empty")
    private Long id;
    @NotBlank(message = "The anime name cannot be null or empty")
    private String name;
}

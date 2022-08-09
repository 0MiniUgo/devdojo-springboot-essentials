package academy.devdojo.springboot.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AnimePostRequestBody {
    @NotBlank(message = "The anime name cannot be null or empty")
    private String name;
}

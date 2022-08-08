package academy.devdojo.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anime anime = (Anime) o;
        return Objects.equals(id, anime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

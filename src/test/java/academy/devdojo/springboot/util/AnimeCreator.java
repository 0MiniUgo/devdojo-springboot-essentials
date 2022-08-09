package academy.devdojo.springboot.util;

import academy.devdojo.springboot.domain.Anime;

public class AnimeCreator {
    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("One Piece")
                .build();
    }
    public static Anime createValidAnime(){
        return Anime.builder()
                .id(1L)
                .name("One Piece")
                .build();
    }
    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .id(1L)
                .name("Two Piece")
                .build();
    }
}

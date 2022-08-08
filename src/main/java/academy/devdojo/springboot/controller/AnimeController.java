package academy.devdojo.springboot.controller;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.service.AnimeService;
import academy.devdojo.springboot.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/animes")
@Log4j2
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    public AnimeController(DateUtil dateUtil, AnimeService animeService) {
        this.dateUtil = dateUtil;
        this.animeService = animeService;
    }

    @GetMapping
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return animeService.listAll();
    }
}

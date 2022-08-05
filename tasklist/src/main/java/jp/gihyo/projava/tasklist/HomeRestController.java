package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HomeRestController {
    @RequestMapping(value="/resthello")
    Mono<String> hello() {
        return Mono.fromCallable(() -> """
                Hello.
                It works!
                現在時刻は%sです。
                """.formatted(LocalDateTime.now()));
    }
}

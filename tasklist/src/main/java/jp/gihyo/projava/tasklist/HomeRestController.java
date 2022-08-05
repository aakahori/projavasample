package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HomeRestController {

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems = new ArrayList<>();

    @RequestMapping(value="/resthello")
    Mono<String> hello() {
        return Mono.fromCallable(() -> """
                Hello.
                It works!
                現在時刻は%sです。
                """.formatted(LocalDateTime.now()));
    }
}

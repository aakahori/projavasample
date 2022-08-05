package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/restadd")
    Mono<String> addItem(@RequestParam("task") String task,
                    @RequestParam("deadline") String deadline) {
        return Mono.fromCallable(() -> {
            String id = UUID.randomUUID().toString().substring(0,8);
            TaskItem item = new TaskItem(id, task, deadline, false);
            taskItems.add(item);

            return "タスクを追加しました。";
        });
    }
    
}

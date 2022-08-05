package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems = new ArrayList<>();

    @RequestMapping(value="/hello")
    Mono<String> hello(Model model) {
        return Mono.fromCallable(() -> {
            model.addAttribute("time", LocalDateTime.now());
            return "hello";
        });
    }

    @GetMapping("/list")
    Mono<String> listItems(Model model) {
        return Mono.fromCallable(() -> {
            model.addAttribute("taskList", taskItems);
            return "home";
        });
    }
}

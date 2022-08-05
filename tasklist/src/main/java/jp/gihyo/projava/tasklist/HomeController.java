package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/add")
    Mono<String> addItem(@RequestParam("task") String task,
            @RequestParam("deadline") String deadline) {
        return Mono.fromCallable(() -> {
            String id = UUID.randomUUID().toString().substring(0, 8);
            TaskItem item = new TaskItem(id, task, deadline, false);
            taskItems.add(item);

            return "redirect:/list";
        });
    }
}

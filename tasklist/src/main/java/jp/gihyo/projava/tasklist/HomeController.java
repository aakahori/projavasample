package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private final TaskListDao dao;

    @Autowired
    HomeController(TaskListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/hello")
    Mono<String> hello(Model model) {
        return Mono.fromCallable(() -> {
            model.addAttribute("time", LocalDateTime.now());
            return "hello";
        });
    }

    @GetMapping("/list")
    Mono<String> listItems(Model model) {
        return dao.findAll()
                .collectList()
                .map(listItems -> {
                    model.addAttribute("taskList", listItems);
                    return "home";
                });
    }

    @GetMapping("/add")
    Mono<String> addItem(@RequestParam("task") String task,
            @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        return dao.add(item)
            .thenReturn("redirect:/list");
    }

    @GetMapping("/delete")
    Mono<String> deleteItem(@RequestParam("id") String id) {
        return dao.delete(id)
            .thenReturn("redirect:/list");
    }

    @GetMapping("/update")
    Mono<String> updateItem(@RequestParam("id") String id,
            @RequestParam("task") String task,
            @RequestParam("deadline") String deadline,
            @RequestParam("done") boolean done) {
        TaskItem taskItem = new TaskItem(id, task, deadline, done);
        return dao.update(taskItem)
            .thenReturn("redirect:/list");
    }
}

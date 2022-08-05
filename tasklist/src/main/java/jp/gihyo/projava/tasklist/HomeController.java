package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    @RequestMapping(value="/hello")
    Mono<String> hello(Model model) {
        return Mono.fromCallable(() -> {
            model.addAttribute("time", LocalDateTime.now());
            return "hello";
        });
    }
}

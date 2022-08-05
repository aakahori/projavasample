package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    @RequestMapping(value="/hello")
    @ResponseBody
    Mono<String> hello() {
        return Mono.fromCallable(() -> """
                <html>
                    <head><title>Hello</title><head>
                    <body>
                        <h1>Hello</h1>
                        It works!<br>
                        現在時刻は%sです。
                    </body>
                <html>
                """.formatted(LocalDateTime.now()));
    }
}

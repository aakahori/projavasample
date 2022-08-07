package jp.gihyo.projava.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import jp.gihyo.projava.tasklist.HomeController.TaskItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskListDao {
    private final R2dbcEntityTemplate r2dbcTemplate;

    @Autowired
    TaskListDao(R2dbcEntityTemplate r2dbcTemplate) {
        this.r2dbcTemplate = r2dbcTemplate;
    }

    public Mono<TaskItem> add(TaskItem taskItem) {
        return r2dbcTemplate.insert(TaskItem.class)
                .into("tasklist")
                .using(taskItem);
    }

    public Flux<TaskItem> findAll() {
        return r2dbcTemplate.select(TaskItem.class)
                .from("tasklist")
                .all();
    }
}

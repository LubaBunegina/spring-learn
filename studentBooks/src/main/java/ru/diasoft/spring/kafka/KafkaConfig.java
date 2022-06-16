package ru.diasoft.spring.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.dto.StudentCommentDto;
import ru.diasoft.spring.service.CommentService;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    private final CommentService commentService;

    public KafkaConfig(CommentService commentService) {
        this.commentService = commentService;
    }

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic bookComment() {
        return TopicBuilder.name("BookComment")
                .partitions(10)
                .replicas(3)
                .compact()
                .build();
    }

    @KafkaListener(id = "myId", topics = "BookComment")
    public void listen(String in) throws JsonProcessingException {
        System.out.println(in);

        StudentCommentDto dto = new ObjectMapper().readValue(in, StudentCommentDto.class);
        Comment comment = commentService.insert(dto.getStudentName(), dto.getBookId(), dto.getBookComment());

        if(comment != null) {
            System.out.println("Comment was added with id = " + comment.getId());
        }
    }

}

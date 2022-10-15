package ru.job4j.pooh;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicServiceTest {

    @Test
    public void whenTopic() {
        Service topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        /* Режим topic. Подписываемся на топик weather. client407. */
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        /* Режим topic. Добавляем данные в топик weather. */
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        /* Режим topic. Забираем данные из индивидуальной очереди в топике weather. Очередь client407. */
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        /* Режим topic. Забираем данные из индивидуальной очереди в топике weather. Очередь client6565.
        Очередь отсутствует, т.к. еще не был подписан - получит пустую строку */
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result1.text()).isEqualTo("temperature=18");
        assertThat(result1.status()).isEqualTo("200");
        assertThat(result2.text()).isEqualTo("");
        assertThat(result2.status()).isEqualTo("204");
    }

    @Test
    public void whenTheRequestTypeIsNeitherPostNorGet() {
        Service queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        /* Забираем данные из очереди weather. Режим queue. Добавление в очередь никогда не производилось */
        Resp result = queueService.process(
                new Req("UPDATE", "topic", "weather", paramForPostMethod)
        );
        assertThat(result.text()).isEqualTo("");
        assertThat(result.status()).isEqualTo("501");
    }
}
package ru.job4j.pooh;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        Service queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        /* Добавляем данные в очередь weather. Режим queue */
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        /* Забираем данные из очереди weather. Режим queue */
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text()).isEqualTo("temperature=18");
        assertThat(result.status()).isEqualTo("200");
    }

    @Test
    public void whenThereIsNoPostThenGetIsAnEmptyString() {
        Service queueService = new QueueService();
        /* Забираем данные из очереди weather. Режим queue. Добавление в очередь никогда не производилось */
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text()).isEqualTo("");
        assertThat(result.status()).isEqualTo("204");
    }

    @Test
    public void whenTheRequestTypeIsNeitherPostNorGet() {
        Service queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        /* Обновляем данные в очереди weather. Режим queue. Добавление в очередь никогда не производилось */
        Resp result = queueService.process(
                new Req("UPDATE", "queue", "weather", paramForPostMethod)
        );
        assertThat(result.text()).isEqualTo("");
        assertThat(result.status()).isEqualTo("501");
    }
}
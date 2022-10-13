package ru.job4j.pooh;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final Map<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String sourceName = req.getSourceName();
        String param = req.getParam();
        if (Objects.equals(req.httpRequestType(), TypeRequest.GET.getValue())) {
            String text = queue.getOrDefault(sourceName, new ConcurrentLinkedQueue<>()).poll();
            return new Resp(text != null ? text : "", Status.OK.getValue());
        }
        queue.putIfAbsent(sourceName, new ConcurrentLinkedQueue<>());
        queue.get(sourceName).add(param);
        return new Resp(param, Status.OK.getValue());
    }
}

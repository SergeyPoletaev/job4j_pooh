package ru.job4j.pooh;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final Map<String, Map<String, ConcurrentLinkedQueue<String>>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String topicName = req.getSourceName();
        String param = req.getParam();
        Resp rsl = new Resp("", Status.NOT_IMPLEMENTED.getValue());
        if (Objects.equals(req.httpRequestType(), TypeRequest.GET.getValue())) {
            queue.putIfAbsent(topicName, new ConcurrentHashMap<>());
            Map<String, ConcurrentLinkedQueue<String>> topicQueue = queue.get(topicName);
            topicQueue.putIfAbsent(param, new ConcurrentLinkedQueue<>());
            String text = topicQueue.get(param).poll();
            rsl = new Resp(text != null ? text : "", text != null ? Status.OK.getValue() : Status.NO_CONTENT.getValue());
        } else if (Objects.equals(req.httpRequestType(), TypeRequest.POST.getValue())) {
            Map<String, ConcurrentLinkedQueue<String>> topicQueue = queue.getOrDefault(topicName, new ConcurrentHashMap<>());
            topicQueue.values().forEach(v -> v.add(param));
            rsl = new Resp(param, Status.OK.getValue());
        }
        return rsl;
    }
}

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
        Resp rsl = new Resp("", Status.NOT_IMPLEMENTED.getValue());
        if (Objects.equals(req.httpRequestType(), TypeRequest.GET.getValue())) {
            String text = queue.getOrDefault(sourceName, new ConcurrentLinkedQueue<>()).poll();
            rsl = new Resp(text != null ? text : "", text != null ? Status.OK.getValue() : Status.NO_CONTENT.getValue());
        } else if (Objects.equals(req.httpRequestType(), TypeRequest.POST.getValue())) {
            queue.putIfAbsent(sourceName, new ConcurrentLinkedQueue<>());
            queue.get(sourceName).add(param);
            rsl = new Resp(param, Status.OK.getValue());
        }
        return rsl;
    }
}

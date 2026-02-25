package ru.translator.eng_rus.Scopes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IdPerRequest {
    private final String requestUUID = UUID.randomUUID().toString().substring(0, 2);

    public String getId() {
        log.info("Initialized new [request] id [{}]", requestUUID);
        return requestUUID;
    }
}

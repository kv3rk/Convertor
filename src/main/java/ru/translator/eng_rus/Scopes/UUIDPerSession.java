package ru.translator.eng_rus.Scopes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Slf4j
@Component
@SessionScope
public class UUIDPerSession {
    private final String sessionId = UUID.randomUUID().toString().substring(0, 4);

    public String getSessionId() {
        log.info("Initialized new [session] id [{}]", sessionId);
        return sessionId;
    }
}

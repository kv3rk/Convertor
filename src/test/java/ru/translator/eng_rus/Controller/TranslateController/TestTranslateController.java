package ru.translator.eng_rus.Controller.TranslateController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.translator.eng_rus.Scopes.UUIDPerSession;
import ru.translator.eng_rus.Service.TranslateService.TranslateService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TranslateController.class)
public class TestTranslateController {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TranslateService translateService;
    @MockitoBean
    private UUIDPerSession uuidPerSession;

    @Test
    void shouldReturnMainPage() throws Exception {
        mockMvc.perform(get("/translate/"))
                .andExpect(status().isOk())
                .andExpect(view().name("translate-page"));
    }

    @Test
    void shouldHandleTranslationRequest() throws Exception {
        when(uuidPerSession.getSessionId()).thenReturn("test-session");
        when(translateService.get(anyString(), anyString())).thenReturn("привет!");
        mockMvc.perform(post("/translate/convert")
                        .param("wrongString", "ghbdtn"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/translate/"))
                .andExpect(flash().attributeExists("pojo"));
    }
}

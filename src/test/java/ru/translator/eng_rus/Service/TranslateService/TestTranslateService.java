package ru.translator.eng_rus.Service.TranslateService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectFactory;
import ru.translator.eng_rus.DTO.DTOTranslate;
import ru.translator.eng_rus.Scopes.IdPerRequest;
import ru.translator.eng_rus.util.TranslateWord;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*; // Импортируем статику Мокито правильно

@ExtendWith(MockitoExtension.class)
public class TestTranslateService {
    @Mock
    private TranslateWord translateWord;
    @Mock
    private ObjectFactory<IdPerRequest> idFactory; // Назовем понятнее
    @Mock
    private IdPerRequest idPerRequest; // Нам нужен сам объект, который вылетит из фабрики
    @Mock
    private DTOTranslate dtoTranslate;

    @InjectMocks
    private TranslateService translateService;

    @Test
    @DisplayName("Should translate and save data correctly")
    void shouldSaveAndReturnTranslateText(){
        // 1. Given (Подготовка)
        String raw = "ghbdtn";
        String translated = "привет";
        String sid = "session-123";

        // Обучаем фабрику возвращать наш объект idPerRequest
        when(idFactory.getObject()).thenReturn(idPerRequest);
        // Обучаем сам объект idPerRequest возвращать какой-то ID
        when(idPerRequest.getId()).thenReturn("456");
        // Обучаем конвертер
        when(translateWord.translateWord(raw)).thenReturn(translated);

        // 2. When (Действие)
        String result = translateService.get(raw, sid);

        // 3. Then (Проверка)
        Assertions.assertEquals(translated, result);

        // ПРОВЕРКА ВЫЗОВА: проверяем, что у dtoTranslate вызвался метод save 1 раз с любым объектом
        verify(dtoTranslate, times(1)).save(any());
    }
}
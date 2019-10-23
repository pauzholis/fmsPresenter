package ru.regme.fmsPresenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.regme.fmsPresenter.model.ExceptionView;

/**
 * Контроллер обработки исключений
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Обработка исключений
     */
    @ExceptionHandler(Exception.class)
    public ExceptionView handleException(Exception e) {
        log.error("{}", e.getMessage(), e);
        return new ExceptionView("Внутренняя ошибка сервера");
    }
}

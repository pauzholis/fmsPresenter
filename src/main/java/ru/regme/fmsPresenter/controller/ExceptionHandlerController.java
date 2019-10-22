package ru.regme.fmsPresenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.regme.fmsPresenter.model.ExceptionView;
import ru.regme.fmsPresenter.model.ServerErrorException;

/**
 * Контроллер обработки исключений
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Обработка собственного исключения
     */
    @ExceptionHandler(ServerErrorException.class)
    public ExceptionView handleException(ServerErrorException e) {
        log.error("response {}", e.getMessage(), e);
        return new ExceptionView("Внутренняя ошибка сервера");
    }

    /**
     * Обработка остальных исключений
     */
    @ExceptionHandler(Exception.class)
    public ExceptionView handleOtherException(Exception e) {
        log.error("response {}", e.getMessage(), e);
        return new ExceptionView("Внутренняя ошибка сервера");
    }
}

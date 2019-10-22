package ru.regme.fmsPresenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.regme.fmsPresenter.model.ResultView;
import ru.regme.fmsPresenter.model.SuccessView;

/**
 * Обработка ответа
 */
@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse response) {
        ResultView resultView = new ResultView();

        if (body instanceof Exception) {
            log.error(((Exception) body).getLocalizedMessage());
        }
        if (body == null) {
            return new SuccessView("success");
        }
        resultView.data = body;
        return resultView;
    }
}

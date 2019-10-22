package ru.regme.fmsPresenter.model;

/**
 * Данные для отображения рузультата обработчика исключений
 */
public class ExceptionView {
    public String error;

    public ExceptionView(String error) {
        this.error = "Внутренняя ошибка сервера";
    }
}

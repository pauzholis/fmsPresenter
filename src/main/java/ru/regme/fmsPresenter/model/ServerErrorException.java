package ru.regme.fmsPresenter.model;

/**
 * Исключение, бросаемое при ошибках, для проброса на фронт
 */
public class ServerErrorException extends Exception {
    public ServerErrorException(String message) {
        super(message);
    }
}

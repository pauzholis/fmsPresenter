package ru.regme.fmsPresenter.service;

import ru.regme.fmsPresenter.model.FederalMigrationServiceDTO;

import java.util.List;

/**
 * Интерфейс для описания методов работы с данными ФМС
 */
public interface FmsService {

    /**
     * Сохранить ФМС в базу данных
     */
    void saveFms() throws Exception;

    /**
     * Получить все отделения ФМС из базы
     *
     * @return все отделения ФМС
     */
    List<FederalMigrationServiceDTO> getAllFms();

    /**
     * Получить ФМС по коду
     *
     * @param fmsCode код ФМС
     * @return ФМС
     */
    List<FederalMigrationServiceDTO> getFmsByCode(String fmsCode) throws Exception;
}

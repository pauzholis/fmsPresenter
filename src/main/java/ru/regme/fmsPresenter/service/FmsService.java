package ru.regme.fmsPresenter.service;

import ru.regme.fmsPresenter.model.FederalMigrationServiceDTO;
import ru.regme.fmsPresenter.model.ServerErrorException;

import java.io.IOException;
import java.util.List;

public interface FmsService {

    /**
     * Сохранить ФМС в базу данных
     */
    void saveFms();

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
    List<FederalMigrationServiceDTO> getFmsByCode(String fmsCode) throws ServerErrorException;
}

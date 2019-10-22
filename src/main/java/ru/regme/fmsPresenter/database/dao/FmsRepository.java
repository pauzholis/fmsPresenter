package ru.regme.fmsPresenter.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regme.fmsPresenter.database.entity.FederalMigrationService;

import java.util.List;

public interface FmsRepository extends JpaRepository<FederalMigrationService, Long> {

    /**
     * Получить список ФМС по коду
     *
     * @param fmsCode код ФМС
     * @return список ФМС
     */
    List<FederalMigrationService> getAllByCode(String fmsCode);

    /**
     * Получить ФМС по коду
     *
     * @param code код отделения ФМС
     * @param name наименование ФМС
     * @return ФМС
     */
    FederalMigrationService getByNameAndCode(String name, String code);
}

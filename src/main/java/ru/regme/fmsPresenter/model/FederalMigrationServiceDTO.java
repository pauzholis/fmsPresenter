package ru.regme.fmsPresenter.model;

import lombok.Data;

/**
 * Класс для описания объекта с данными о ФМС, передаваемый на фронт
 */
@Data
public class FederalMigrationServiceDTO {

    /**
     * Наименование отделения
     */
    private String name;

    /**
     * Код отделения
     */
    private String code;

    public FederalMigrationServiceDTO() {
    }

    public FederalMigrationServiceDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

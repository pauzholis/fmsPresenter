package ru.regme.fmsPresenter.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Федеральная Миграционная Служба
 */
@Data
@Entity
@Table(name = "federal_migration_service")
public class FederalMigrationService {

    /**
     * Идентификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование отделения
     */
    @Column(nullable = false)
    private String name;

    /**
     * Код отделения
     */
    @Column(nullable = false)
    private String code;

    public FederalMigrationService() {
    }

    public FederalMigrationService(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

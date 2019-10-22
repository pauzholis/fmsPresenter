package ru.regme.fmsPresenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.regme.fmsPresenter.model.FederalMigrationServiceDTO;
import ru.regme.fmsPresenter.service.FmsService;

import java.util.List;

/**
 * Контроллер для возврата json с данными ФМС на фронт
 */
@RestController
@RequestMapping(value = "/fms-presenter", produces = MediaType.APPLICATION_JSON_VALUE)
public class FmsController {

    private FmsService fmsService;

    @Autowired
    public FmsController(FmsService fmsService) {
        this.fmsService = fmsService;
    }

    /**
     * Вернуть все отделения ФМС
     *
     * @return все отделения ФМС
     */
    @GetMapping(value = "/fms", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FederalMigrationServiceDTO> getAllFms() {
        return fmsService.getAllFms();
    }

    /**
     * Вернуть ФМС по коду
     *
     * @param fmsCode код отделения ФМС
     * @return ФМС
     */
    @GetMapping(value = "/fms/{fmsCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FederalMigrationServiceDTO> getFms(@PathVariable("fmsCode") String fmsCode)
            throws Exception {
        return fmsService.getFmsByCode(fmsCode);
    }

    /**
     * Получить архив с данными о ФМС и сохранить в базу
     */
    @GetMapping(value = "/fms/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveFms() throws Exception {
        fmsService.saveFms();
    }
}

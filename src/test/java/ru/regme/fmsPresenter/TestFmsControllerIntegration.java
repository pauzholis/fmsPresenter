package ru.regme.fmsPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.regme.fmsPresenter.controller.FmsController;
import ru.regme.fmsPresenter.model.FederalMigrationServiceDTO;
import ru.regme.fmsPresenter.service.FmsService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FmsController.class)
public class TestFmsControllerIntegration {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FmsService service;

    @Test
    public void givenFmsList_whenGetAllFms_thenReturnJsonArray()
            throws Exception {

        FederalMigrationServiceDTO fms = new FederalMigrationServiceDTO(
                "ОУФМС РОССИИ ПО ПЕНЗЕНСКОЙ ОБЛ. В ЛЕНИНСКОМ Р-НЕ Г. ПЕНЗЫ", "580-001"
        );

        List<FederalMigrationServiceDTO> allFms = Collections.singletonList(fms);

        given(service.getAllFms()).willReturn(allFms);

        mvc.perform(get("/fms")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name", is(fms.getName())));
    }

    @Test
    public void givenFms_whenGetFmsByCode_thenReturnJsonArray()
            throws Exception {

        FederalMigrationServiceDTO fms = new FederalMigrationServiceDTO(
                "ОУФМС РОССИИ ПО ПЕНЗЕНСКОЙ ОБЛ. В ЛЕНИНСКОМ Р-НЕ Г. ПЕНЗЫ", "580-001"
        );

        List<FederalMigrationServiceDTO> allFms = Collections.singletonList(fms);

        String code = "580-001";

        given(service.getFmsByCode(code)).willReturn(allFms);

        mvc.perform(get("/fms/".concat(code))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name", is(fms.getName())));
    }
}

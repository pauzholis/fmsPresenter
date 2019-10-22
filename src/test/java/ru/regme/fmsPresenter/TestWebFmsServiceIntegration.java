package ru.regme.fmsPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.regme.fmsPresenter.database.dao.FmsRepository;
import ru.regme.fmsPresenter.database.entity.FederalMigrationService;
import ru.regme.fmsPresenter.model.FederalMigrationServiceDTO;
import ru.regme.fmsPresenter.service.FmsService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebFmsServiceIntegration {

    @Autowired
    private FmsService fmsService;

    @MockBean
    private FmsRepository fmsRepository;

    @Test
    public void getFmsByCodeTest() throws Exception {
        given(this.fmsRepository.getAllByCode(any())).willReturn(
                Collections.singletonList(
                        new FederalMigrationService(
                                "ОУФМС РОССИИ ПО ПЕНЗЕНСКОЙ ОБЛ. В ЛЕНИНСКОМ Р-НЕ Г. ПЕНЗЫ", "580-001"
                        )
                )
        );
        List<FederalMigrationServiceDTO> fmsDtoList = fmsService.getFmsByCode("580-001");
        assertThat(fmsDtoList.get(0).getCode()).isEqualTo("580-001");
    }

    @Test
    public void getAllFmsTest() {
        given(this.fmsRepository.findAll()).willReturn(
                Collections.singletonList(
                        new FederalMigrationService(
                                "ОУФМС РОССИИ ПО ПЕНЗЕНСКОЙ ОБЛ. В ЛЕНИНСКОМ Р-НЕ Г. ПЕНЗЫ", "580-001"
                        )
                )
        );
        List<FederalMigrationServiceDTO> fmsDtoList = fmsService.getAllFms();
        assertThat(fmsDtoList.get(0).getCode()).isEqualTo("580-001");
    }
}

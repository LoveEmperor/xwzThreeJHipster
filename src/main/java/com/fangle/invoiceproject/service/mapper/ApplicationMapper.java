package com.fangle.invoiceproject.service.mapper;

import com.fangle.invoiceproject.domain.*;
import com.fangle.invoiceproject.service.dto.ApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Application and its DTO ApplicationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApplicationMapper extends EntityMapper<ApplicationDTO, Application> {



    default Application fromId(Long id) {
        if (id == null) {
            return null;
        }
        Application application = new Application();
        application.setId(id);
        return application;
    }
}

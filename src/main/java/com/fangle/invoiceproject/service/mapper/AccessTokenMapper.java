package com.fangle.invoiceproject.service.mapper;

import com.fangle.invoiceproject.domain.*;
import com.fangle.invoiceproject.service.dto.AccessTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccessToken and its DTO AccessTokenDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccessTokenMapper extends EntityMapper<AccessTokenDTO, AccessToken> {


    default AccessToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccessToken accessToken = new AccessToken();
        accessToken.setId(id);
        return accessToken;
    }
}

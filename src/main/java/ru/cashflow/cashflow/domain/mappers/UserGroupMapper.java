package ru.cashflow.cashflow.domain.mappers;

import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.data.entities.UserGroupDbo;
import ru.cashflow.cashflow.domain.models.UserGroup;


@Component
public class UserGroupMapper implements DBOMapper<UserGroup, UserGroupDbo> {

    @Override
    public UserGroupDbo toDBO(UserGroup model) {
        return new UserGroupDbo(model.getId(), model.getName());    }

    @Override
    public UserGroup toModel(UserGroupDbo dto) {
        return new UserGroup(dto.getId(),
        dto.getName());    
    }
    
}

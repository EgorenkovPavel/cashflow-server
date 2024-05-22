package ru.cashflow.cashflow.domain.mappers;

import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.data.entities.UserDbo;
import ru.cashflow.cashflow.domain.models.User;

@Component
public class UserMapper implements DBOMapper<User, UserDbo> {

    private final UserGroupMapper userGroupMapper;

    public UserMapper(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    public UserDbo toDBO(User user) {
        return new UserDbo(
            user.getId(), 
            user.getName(), 
            user.getPassword(),
            user.getEmail(), 
            userGroupMapper.toDBO(user.getGroup()));
    }

    @Override
    public User toModel(UserDbo user) {
        return new User(
            user.getId(),
            user.getName(), 
            user.getPassword(),
            user.getEmail(), 
            userGroupMapper.toModel(user.getGroup()));
    }

}

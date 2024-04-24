package ru.cashflow.cashflow.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.cashflow.cashflow.data.repos.UserGroupRepository;
import ru.cashflow.cashflow.data.repos.UserRepository;
import ru.cashflow.cashflow.domain.mappers.UserGroupMapper;
import ru.cashflow.cashflow.domain.mappers.UserMapper;
import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.models.UserGroup;

@Service
public class UserService {
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final UserGroupMapper groupMapper;

    public UserService(
        UserGroupRepository userGroupRepository, 
        UserRepository userRepository, 
        UserMapper userMapper,
        UserGroupMapper groupMapper) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
    }

    public List<UserGroup> findAllGroups() {
        return userGroupRepository.findAll()
                .stream()
                .map(userGroup -> groupMapper.toModel(userGroup))
                .toList();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.toModel(user))
                .toList();
    }

    public List<User> findUsersByGroup(UserGroup group) {
        return userRepository.findByGroup(groupMapper.toDBO(group))
                .stream()
                .map(user -> userMapper.toModel(user))
                .toList();
    }

    public void saveGroup(UserGroup group){
        userGroupRepository.save(groupMapper.toDBO(group));
    }

    public void saveUser(User user){
        userRepository.save(userMapper.toDBO(user));
    }

    public Optional<UserGroup> findUserGroupById(Long id){
        return userGroupRepository.findById(id).map(group -> groupMapper.toModel(group));
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id).map(user -> userMapper.toModel(user));
    }
}

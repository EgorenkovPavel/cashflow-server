package ru.cashflow.cashflow.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
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

    public Optional<User> findUserByName(String name){
        return userRepository.findByName(name).map(user -> userMapper.toModel(user));
    }

    public User saveOrUpdateUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<User> existingUser = userRepository.findByEmail(email).map(user -> userMapper.toModel(user));

        User userEntity;
        if (existingUser.isPresent()) {
            userEntity = existingUser.get();
            // Обновление информации пользователя
            userEntity.setName(oAuth2User.getAttribute("name"));
        } else {
            userEntity = new User();
            userEntity.setEmail(email);
            userEntity.setName(oAuth2User.getAttribute("name"));
            // Установка дополнительных полей по необходимости
        }

        return userMapper.toModel(userRepository.save(userMapper.toDBO(userEntity)));
    }
}

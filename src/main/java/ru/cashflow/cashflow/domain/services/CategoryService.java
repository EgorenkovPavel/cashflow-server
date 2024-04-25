package ru.cashflow.cashflow.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.cashflow.cashflow.data.repos.CategoryRepository;
import ru.cashflow.cashflow.domain.mappers.CategoryMapper;
import ru.cashflow.cashflow.domain.mappers.UserGroupMapper;
import ru.cashflow.cashflow.domain.models.Category;
import ru.cashflow.cashflow.domain.models.UserGroup;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserGroupMapper groupMapper;
    
    public CategoryService(
        CategoryMapper categoryMapper, 
        CategoryRepository categoryRepository, 
        UserGroupMapper groupMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.groupMapper = groupMapper;
    }

    public List<Category> findCategoriesByUserGroup(UserGroup group){
        return categoryRepository.findByUserGroup(groupMapper.toDBO(group))
        .stream()
        .map(category -> categoryMapper.toModel(category))
        .toList();
    }

    public void saveCategory(Category category){
        categoryRepository.save(categoryMapper.toDBO(category));
    }

}

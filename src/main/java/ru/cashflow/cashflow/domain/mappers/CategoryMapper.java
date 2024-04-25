package ru.cashflow.cashflow.domain.mappers;

import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.data.entities.CategoryDbo;
import ru.cashflow.cashflow.domain.models.Category;

@Component
public class CategoryMapper implements DBOMapper<Category, CategoryDbo> {

    private final UserGroupMapper groupMapper;

    public CategoryMapper(UserGroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public CategoryDbo toDBO(Category model) {
        return new CategoryDbo(
            model.getId(), 
            model.getName(), 
            toDBOType(model.getType()), 
            groupMapper.toDBO(model.getUserGroup()));
    }

    @Override
    public Category toModel(CategoryDbo dbo) {
        return new Category(
            dbo.getId(),
            dbo.getName(),
            toModelType(dbo.getType()),
            groupMapper.toModel(dbo.getUserGroup())
        );
    }
    

    private CategoryDbo.Type toDBOType(Category.Type type){
        switch (type) {
            case INPUT:
                return CategoryDbo.Type.INPUT;
            case OUTPUT:
                return CategoryDbo.Type.OUTPUT;     
            default:
                return null;
        }
    }

    private Category.Type toModelType(CategoryDbo.Type type){
        switch (type) {
            case INPUT:
                return Category.Type.INPUT;
            case OUTPUT:
                return Category.Type.OUTPUT;  
            default:
                return null;
        }
    }
}

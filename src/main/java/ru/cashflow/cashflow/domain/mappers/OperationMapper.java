package ru.cashflow.cashflow.domain.mappers;

import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.data.entities.OperationDbo;
import ru.cashflow.cashflow.domain.models.Operation;

@Component
public class OperationMapper implements DBOMapper<Operation, OperationDbo> {

    private final UserMapper userMapper;
    private final UserGroupMapper groupMapper;
    private final AccountMapper accountMapper;
    private final CategoryMapper categoryMapper;

    public OperationMapper(
        UserGroupMapper groupMapper, 
        UserMapper userMapper, 
        AccountMapper accountMapper, 
        CategoryMapper categoryMapper) {
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
        this.accountMapper = accountMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public OperationDbo toDBO(Operation model) {
        return new OperationDbo(
            model.getId(),
            model.getDate(),
            toDBOType(model.getType()),
            accountMapper.toDBO(model.getAccount()),
            categoryMapper.toDBO(model.getCategory()),
            accountMapper.toDBO(model.getRecAccount()),
            model.getSum(),
            userMapper.toDBO(model.getUser()),
            groupMapper.toDBO(model.getUserGroup())
        );
    }

    @Override
    public Operation toModel(OperationDbo dbo) {
        return new Operation(
            dbo.getId(),
            dbo.getDate(),
            accountMapper.toModel(dbo.getAccount()),
            toModelType(dbo.getType()),
            categoryMapper.toModel(dbo.getCategory()),
            accountMapper.toModel(dbo.getRecAccount()),
            dbo.getSum(),
            userMapper.toModel(dbo.getUser()),
            groupMapper.toModel(dbo.getUserGroup())
        );
    }
    
    private OperationDbo.Type toDBOType(Operation.Type type){
        switch (type) {
            case INPUT:
                return OperationDbo.Type.INPUT;
            case OUTPUT:
                return OperationDbo.Type.OUTPUT; 
            case TRANSFER:
                return OperationDbo.Type.TRANSFER;      
            default:
                return null;
        }
    }

    private Operation.Type toModelType(OperationDbo.Type type){
        switch (type) {
            case INPUT:
                return Operation.Type.INPUT;
            case OUTPUT:
                return Operation.Type.OUTPUT; 
            case TRANSFER:
                return Operation.Type.TRANSFER;  
            default:
                return null;
        }
    }
}

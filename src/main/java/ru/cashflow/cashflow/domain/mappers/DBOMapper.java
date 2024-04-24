package ru.cashflow.cashflow.domain.mappers;

public interface DBOMapper<M, D> {
    public D toDBO(M model);
    public M toModel(D dbo);
}

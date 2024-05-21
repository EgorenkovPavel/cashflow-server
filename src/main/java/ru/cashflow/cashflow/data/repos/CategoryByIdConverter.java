package ru.cashflow.cashflow.data.repos;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.domain.models.Category;
import ru.cashflow.cashflow.domain.services.CategoryService;


@Component
public class CategoryByIdConverter implements Converter<String, Category> {
        private CategoryService repo;

        public CategoryByIdConverter(CategoryService repo) {
                this.repo = repo;
        }

        @Override
        public Category convert(String id) {
                return repo.findCategoryById(Long.parseLong(id)).orElse(null);
        }

}

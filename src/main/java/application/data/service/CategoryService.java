package application.data.service;

import application.data.model.Category;
import application.data.repository.CategoryRepository;
import application.model.viewmodel.common.ChartLabelDataVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);



    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void addNewListCategories(List<Category> categoryList) {
        try {
            categoryRepository.save(categoryList);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public Category findOne(int categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    public List<Category> getListAllCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<ChartLabelDataVM> countBookByCategory() {
        return categoryRepository.countBookByCategory();
    }
    public List<ChartLabelDataVM> sumBookByCategory() {
        return categoryRepository.sumBookByCategory();
    }

}


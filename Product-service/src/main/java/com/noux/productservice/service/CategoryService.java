package com.noux.productservice.service;
import com.noux.productservice.entity.Category;
import com.noux.productservice.exception.CategoryNotFoundException;
import com.noux.productservice.repository.CategoryRepository;
import com.noux.productservice.request.category.DTO.CategoryRequest;
import com.noux.productservice.request.category.mapper.CategoryRequestMapper;
import com.noux.productservice.response.category.DTO.CategoryResponse;
import com.noux.productservice.response.category.mapper.CategoryResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryRequestMapper categoryRequestMapper;

    private final CategoryResponseMapper categoryResponseMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository ,
                           CategoryRequestMapper categoryRequestMapper,
                           CategoryResponseMapper categoryResponseMapper){
        this.categoryRepository= categoryRepository;
        this.categoryRequestMapper= categoryRequestMapper;
        this.categoryResponseMapper= categoryResponseMapper;
    }

     public CategoryResponse createCategory(CategoryRequest categoryRequest){
        var category = categoryRequestMapper.requestMapping(categoryRequest);
        var savedCategory = categoryRepository.save(category);
        return categoryResponseMapper.responseMapping(savedCategory);
     }

     public CategoryResponse updateCategory(Long id , CategoryRequest categoryRequest){
        var existingCategory= categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found "
                ));
        var request = categoryRequestMapper.requestMapping(categoryRequest);
         if (request.getName() != null) {
             existingCategory.setName(request.getName());
         }
         if (request.getDescription() != null) {
             existingCategory.setDescription(request.getDescription());
         }
        var savedCategory = categoryRepository.save(existingCategory);
        return categoryResponseMapper.responseMapping(savedCategory);
     }


     public void deleteCategory(Long id){
         var existingCategory= categoryRepository.findById(id)
                 .orElseThrow(() -> new CategoryNotFoundException(
                         "Category not found "
                 ));
         categoryRepository.delete(existingCategory);
     }

     public List<CategoryResponse> getAllCategories(){
       List<Category> categories = categoryRepository.findAll();
       return categories.stream()
               .map(categoryResponseMapper::responseMapping)
               .collect(Collectors.toList());
     }

     public CategoryResponse getCategory(Long id){
         var existingCategory= categoryRepository.findById(id)
                 .orElseThrow(() -> new CategoryNotFoundException(
                         "Category not found "
                 ));
         return categoryResponseMapper.responseMapping(existingCategory);
     }

     public CategoryResponse findCategoryByName(String Name ){
        var category = categoryRepository.findCategoryByName(Name);
        return categoryResponseMapper.responseMapping(category);
     }



}

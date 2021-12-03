package com.ddlele.moneytrack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ddlele.moneytrack.Model.CategoryRepository;
import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.Category;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private CategoryRepository repository;

    private Category edited;

    public Category getEdited() {
        return edited;
    }

    public void setEdited(Category edited) {
        this.edited = edited;
    }

    public CategoryViewModel(){
        repository = CategoryRepository.getInstance();
    }

    public LiveData<List<Category>> getAll(){
        return repository.getAllCategories();
    }


    public Category create(Category item){
        return repository.create(item);
    }

    public Category update(Category item){
        return repository.update(item);
    }

    public Category delete(int id){
        return repository.delete(id);
    }
    public Category get(int id){
        return repository.get(id);
    }
}

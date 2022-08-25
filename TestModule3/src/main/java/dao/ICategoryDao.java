package dao;

import Model.Category;

import java.util.List;

public interface ICategoryDao {
    public List<Category> selectAllCategory();
    public Category selectTypeByID(int id);
}

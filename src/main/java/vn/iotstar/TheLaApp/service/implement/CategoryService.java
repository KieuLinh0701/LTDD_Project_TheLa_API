package vn.iotstar.TheLaApp.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.TheLaApp.dto.CategoryDto;
import vn.iotstar.TheLaApp.entity.Category;
import vn.iotstar.TheLaApp.repository.CategoryRepository;
import vn.iotstar.TheLaApp.service.ICategoryService;
import vn.iotstar.TheLaApp.util.ConvertToDto;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ConvertToDto convert;
	
	public List<CategoryDto> getAllActiveAndNotDeletedCategories() {
		List<Category> list = repository.getAllActiveAndNotDeletedCategories();
		
		return convert.convertToListCategoryDto(list);
	}
}

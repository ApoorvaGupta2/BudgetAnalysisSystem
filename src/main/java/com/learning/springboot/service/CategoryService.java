package com.learning.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.CategoryAE;
import com.learning.springboot.beans.de.CategoryDE;
import com.learning.springboot.repository.CategoryRepository;
import com.learning.springboot.type.TransactionType;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void submitCategory(CategoryAE categoryAE) {

		CategoryDE categoryDE = convert(categoryAE);
		categoryRepository.save(categoryDE);
	}

	public List<CategoryAE> getActiveCategories() {

		List<CategoryDE> deList = (List<CategoryDE>) categoryRepository.findAllByDeletedFalse();
		List<CategoryAE> aeList = new ArrayList<CategoryAE>();
		for (int i = 0; i < deList.size(); i++) {
			CategoryDE categoryDE = deList.get(i);
			aeList.add(convert(categoryDE));
		}
		return aeList;
	}

	public CategoryAE getCategoryById(int id) {
		CategoryDE categoryDE = categoryRepository.findById(id).orElse(new CategoryDE());
		return convert(categoryDE);

	}
	
	public CategoryDE getCategoryDEById(int id) {
		CategoryDE categoryDE = categoryRepository.findById(id).orElse(new CategoryDE());
		return categoryDE;

	}

	public void delete(int id) {
		CategoryDE categoryDE = categoryRepository.findById(id).orElse(new CategoryDE());
		categoryDE.setDeleted(true);
		categoryRepository.save(categoryDE);
	}

	private CategoryDE convert(CategoryAE ae) {
		CategoryDE de = new CategoryDE();
		de.setName(ae.getName());
		de.setType(ae.getType());
		de.setId(ae.getId());
		de.setBudget(ae.getBudget());
		return de;
	}

	private CategoryAE convert(CategoryDE de) {
		CategoryAE ae = new CategoryAE();
		ae.setName(de.getName());
		ae.setType(de.getType());
		ae.setId(de.getId());
		ae.setBudget(de.getBudget());
		return ae;
	}

	public List<CategoryAE> getActiveCategoriesByType(TransactionType type) {
		List<CategoryDE> deList = (List<CategoryDE>) categoryRepository.findAllByDeletedFalseAndType(type);
		List<CategoryAE> aeList = new ArrayList<CategoryAE>();
//		deList.stream().forEach(e -> aeList.add(convert(e)));
		aeList = deList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return aeList;

	}

}

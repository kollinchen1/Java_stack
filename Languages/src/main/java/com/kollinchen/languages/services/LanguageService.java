package com.kollinchen.languages.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.kollinchen.languages.models.Language;
import com.kollinchen.languages.repositories.LanguageRepository;
@Service
public class LanguageService {
	private final LanguageRepository languageRepository;

	 public LanguageService(LanguageRepository languageRepository) {
	     this.languageRepository = languageRepository;
	 }
	 // returns all the languages
	 public List<Language> allLanguages() {
	     return languageRepository.findAll();
	 }
	 // creates a lan
	 public Language createLanguage(Language lan) {
	     return languageRepository.save(lan);
	 }
	 // retrieves a lan
	 public Language findLanguage(Long id) {
		 return languageRepository.findById(id).orElse(null);
	 }
	 public Language updateLanguage(Language lan) {
		 languageRepository.save(lan);
		 return lan;
	 }

	 public Language updateLanguage(Long id, String name, String creator, String currentVersion) {
		 Language lan = this.findLanguage(id);
		 lan.setName(name);
		 lan.setCreator(creator);
		 lan.setCurrentVersion(currentVersion);
		 languageRepository.save(lan);
		 return lan;
	 }
	 // Delete a lan
	 public void deleteLanguage(Long id) {
		 languageRepository.deleteById(id);
	 }
}

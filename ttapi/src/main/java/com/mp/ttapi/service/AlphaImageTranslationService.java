package com.mp.ttapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.ImageTranslationDAO;
import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;
import com.mp.ttapi.dto.ImageChecksumDTO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaImageTranslationService implements ImageTranslationService{

	@Autowired
	private ImageTranscriptionService imageTranscriptionService;
	
	@Autowired
	private ImageTranslationDAO imageTranslationDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ImageChecksumDTO createImageTranslation(int transcriptionId, String translationText) {
		ImageTranscription transcription = imageTranscriptionService.getImageTranscription(transcriptionId);
		ImageTranslation it = imageTranslationDao.getImageTranslationByTranscription(transcription);
		boolean newIt = false;
		if(transcription == null || transcriptionId == 0){
			return null;
		}
		if(it == null || it.getId() == 0){
			it = new ImageTranslation();
			newIt = true;
			
		}
		it.setTranslationText(translationText);
		it.setImageTranscription(transcription);
		int wordcount = translationText.length() - translationText.replaceAll(" ", "").length() + 1;
		it.setWordCount(wordcount);
		if(newIt){
			imageTranslationDao.addImageTranslation(it);
		}
		
		ImageChecksumDTO icdto = new ImageChecksumDTO();
		icdto.setTranslationId(it.getId());
		icdto.setTranslationText(it.getTranslationText());
		icdto.setTranslationWordCount(it.getWordCount());
		return icdto;
	}
}

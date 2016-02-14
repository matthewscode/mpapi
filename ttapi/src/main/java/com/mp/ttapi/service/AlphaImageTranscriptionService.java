package com.mp.ttapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.FileTranslationDAO;
import com.mp.ttapi.dao.ImageTranscriptionDAO;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.dto.ImageChecksumDTO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaImageTranscriptionService implements ImageTranscriptionService{

	@Autowired
	private FileTranslationService fileTranslationService;
	
	@Autowired
	private ImageTranscriptionDAO imageTranscriptionDao;
	@Autowired 
	private FileTranslationDAO fileTranslationDao;
	
	@Override
	@Transactional
	public ImageTranscription getImageTranscription(int id) {
		return imageTranscriptionDao.getImageTranscription(id);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ImageChecksumDTO createImageTranscription(int checksumId, String transcriptionText) {
		ImageChecksum ic = fileTranslationService.getImageChecksum(checksumId);
		ImageTranscription it = imageTranscriptionDao.getImageTranscriptionByChecksumId(ic);
		if(it == null || it.getId() == 0){
			it = new ImageTranscription();
		}
		it.setTranscriptionText(transcriptionText);
		int wordcount = (transcriptionText.length() - transcriptionText.replaceAll(" ", "").length()) + 1;
		it.setWordCount(wordcount);
		it.setImageChecksum(ic);
		imageTranscriptionDao.addImageTranscription(it);
		ic.setImageTranscription(it);
		fileTranslationDao.createImageChecksum(ic);
		ImageChecksumDTO icdto = new ImageChecksumDTO();
		icdto.setChecksumId(checksumId);
		icdto.setTranscriptionId(it.getId());
		icdto.setTranscriptionText(it.getTranscriptionText());
		icdto.setTranscriptionWordCount(it.getWordCount());
		return icdto;
	}
}

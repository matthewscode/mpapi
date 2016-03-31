package com.mp.ttapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.FileTranslationDAO;
import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;
import com.mp.ttapi.dto.FileTranslationDTO;
import com.mp.ttapi.dto.ImageChecksumDTO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FileTranslationServiceImpl implements FileTranslationService {
	
	@Autowired
	private FileTranslationDAO fileTranslationDAO;
	
	@Override
	@Transactional
	public List<FileTranslation> getAllFileTranslations() {
		return fileTranslationDAO.getAllFileTranslations();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createImageChecksum(int checksum) {
		ImageChecksum ic = new ImageChecksum();
		ic.setChecksum(checksum);
		return fileTranslationDAO.createImageChecksum(ic);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createFileTranslation(int checksum, String originUrl) {
		FileTranslation ft = new FileTranslation();
		ImageChecksum ic = getImageChecksumByChecksum(checksum);
		ft.setImageChecksum(ic);
		ft.setOriginUrl(originUrl);
		return fileTranslationDAO.createFileTranslation(ft);
	}
	
	@Override
	@Transactional
	public ImageChecksum getImageChecksum(int id) {
		return fileTranslationDAO.getImageChecksum(id);
	}

	@Override
	@Transactional
	public ImageChecksum getImageChecksumByChecksum(int checksum) {
		return fileTranslationDAO.getImageChecksumByChecksum(checksum);
	}

	@Override
	public List<FileTranslationDTO> getAllFileTranslationsForDisplay() {
		List<FileTranslation> fileTranslationList = getAllFileTranslations();
		List<FileTranslationDTO> ftDtoList = convertFileTranslationsToDTO(fileTranslationList);
		return ftDtoList;
	}
	
	@Override
	@Transactional
	public List<FileTranslationDTO> convertFileTranslationsToDTO(List<FileTranslation> fileTranslationList){
		List<FileTranslationDTO> ftDtoList = new ArrayList<>();
		for(FileTranslation ft : fileTranslationList){
			FileTranslationDTO newFt = new FileTranslationDTO();
			ImageTranscription transcription = ft.getImageChecksum().getImageTranscription();
			newFt.setId(ft.getId());
			newFt.setChecksumId(ft.getImageChecksum().getId());
			newFt.setChecksum(ft.getImageChecksum().getChecksum());
			newFt.setOriginUrl(ft.getOriginUrl());
			boolean hasTranscription = false;
			boolean hasTranslation = false;
			if(transcription != null && transcription.getTranscriptionText().length() > 0){
				hasTranscription = true;
				List<ImageTranslation> itList = transcription.getImageTranslationList();
				if(itList != null && itList.size() > 0 && itList.get(0).getTranslationText().length() > 0){
					hasTranslation = true;
				}
			newFt.setHasTranscription(hasTranscription);
			newFt.setHasTranslation(hasTranslation);
			}
			ftDtoList.add(newFt);
		}
		return ftDtoList;
	}

	@Override
	@Transactional
	public List<FileTranslationDTO> getFileTranslationsByRow(int start, int stop) {
		List<FileTranslation> ftList = fileTranslationDAO.getFileTranslationsByRow(start, stop);
		return convertFileTranslationsToDTO(ftList);
	}

	@Override
	@Transactional
	public ImageChecksumDTO getImageChecksumDto(int checksumId) {
		ImageChecksumDTO icDto = new ImageChecksumDTO();
		ImageChecksum ic = getImageChecksum(checksumId);
		icDto.setChecksumId(ic.getId());
		ImageTranscription transcription = ic.getImageTranscription();
		if(transcription != null && transcription.getTranscriptionText().length() > 0){
			icDto.setTranscriptionId(transcription.getId());
			icDto.setTranscriptionText(transcription.getTranscriptionText());
			icDto.setTranscriptionWordCount(transcription.getWordCount());
			List<ImageTranslation> itList = transcription.getImageTranslationList();
			if(itList != null && itList.size() > 0 && itList.get(0).getTranslationText().length() > 0){
				icDto.setTranslationId(itList.get(0).getId());
				icDto.setTranslationText(itList.get(0).getTranslationText());
				icDto.setTranslationWordCount(itList.get(0).getWordCount());
			}
		}
		return icDto;	
	}
}

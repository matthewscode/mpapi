package com.mp.ttapi.service;

import java.util.List;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.dto.FileTranslationDTO;
import com.mp.ttapi.dto.ImageChecksumDTO;

public interface FileTranslationService {
	
	public List<FileTranslation> getAllFileTranslations();

	public boolean createImageChecksum(int checksum);

	public boolean createFileTranslation(int checksum, String originUrl);
	
	public ImageChecksum getImageChecksum(int id);
	
	public ImageChecksum getImageChecksumByChecksum(int checksum);

	public List<FileTranslationDTO> getAllFileTranslationsForDisplay();

	public List<FileTranslationDTO> getFileTranslationsByRow(int start, int stop);

	List<FileTranslationDTO> convertFileTranslationsToDTO(
			List<FileTranslation> fileTranslationList);

	public ImageChecksumDTO getImageChecksumDto(int checksumId);
	
}

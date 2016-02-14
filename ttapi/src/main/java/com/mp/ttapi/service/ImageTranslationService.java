package com.mp.ttapi.service;

import com.mp.ttapi.dto.ImageChecksumDTO;

public interface ImageTranslationService {
	
	public ImageChecksumDTO createImageTranslation(int transcriptionId, String transcriptionText);
	
}

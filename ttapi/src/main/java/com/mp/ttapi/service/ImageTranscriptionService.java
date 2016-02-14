package com.mp.ttapi.service;

import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.dto.ImageChecksumDTO;

public interface ImageTranscriptionService {
	
	public ImageChecksumDTO createImageTranscription(int id, String transcriptionText);
	
	public ImageTranscription getImageTranscription(int id);
}

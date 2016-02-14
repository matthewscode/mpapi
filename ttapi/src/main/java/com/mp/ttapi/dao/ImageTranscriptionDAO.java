package com.mp.ttapi.dao;

import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;

public interface ImageTranscriptionDAO {

	public void addImageTranscription(ImageTranscription it);

	public ImageTranscription getImageTranscription(int id);

	public ImageTranscription getImageTranscriptionByChecksumId(ImageChecksum ic);

}

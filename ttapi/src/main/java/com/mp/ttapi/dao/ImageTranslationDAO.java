package com.mp.ttapi.dao;

import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;

public interface ImageTranslationDAO {

	public void addImageTranslation(ImageTranslation it);

	public ImageTranslation getImageTranslation(int id);

	public ImageTranslation getImageTranslationByTranscription(ImageTranscription it);

}

package com.mp.ttapi.dao;

import java.util.List;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;

public interface FileTranslationDAO {
	
	public List<FileTranslation> getAllFileTranslations();

	public boolean createImageChecksum(ImageChecksum ic);

	public ImageChecksum getImageChecksumByChecksum(int checksum);

	public boolean createFileTranslation(FileTranslation ft);

	public List<FileTranslation> getFileTranslationsByRow(int start, int stop);

	public ImageChecksum getImageChecksum(int id);
	
}

package com.mp.ttapi.dto;


public class FileTranslationDTO {
	
	private long id;
	private int checksumId;
	private int checksum;
	private String originUrl;
	private int creationDate;
	private boolean hasTranscription;
	private boolean hasTranslation;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getChecksumId() {
		return checksumId;
	}
	public void setChecksumId(int checksumId) {
		this.checksumId = checksumId;
	}
	public int getChecksum() {
		return checksum;
	}
	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public int getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(int creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isHasTranscription() {
		return hasTranscription;
	}
	public void setHasTranscription(boolean hasTranscription) {
		this.hasTranscription = hasTranscription;
	}
	public boolean isHasTranslation() {
		return hasTranslation;
	}
	public void setHasTranslation(boolean hasTranslation) {
		this.hasTranslation = hasTranslation;
	}
	
}

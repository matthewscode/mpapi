package com.mp.ttapi.dto;

public class ImageChecksumDTO {
	
	private int checksumId;
	private int transcriptionId;
	private String transcriptionText;
	private int transcriptionWordCount;
	private int translationId;
	private String translationText;
	private int translationWordCount;
	
	public int getChecksumId() {
		return checksumId;
	}
	public void setChecksumId(int checksumId) {
		this.checksumId = checksumId;
	}
	public int getTranscriptionId() {
		return transcriptionId;
	}
	public void setTranscriptionId(int transcriptionId) {
		this.transcriptionId = transcriptionId;
	}
	public int getTranslationId() {
		return translationId;
	}
	public void setTranslationId(int translationId) {
		this.translationId = translationId;
	}
	public String getTranscriptionText() {
		return transcriptionText;
	}
	public void setTranscriptionText(String transcriptionText) {
		this.transcriptionText = transcriptionText;
	}
	public String getTranslationText() {
		return translationText;
	}
	public void setTranslationText(String translationText) {
		this.translationText = translationText;
	}
	public int getTranscriptionWordCount() {
		return transcriptionWordCount;
	}
	public void setTranscriptionWordCount(int transcriptionNoWords) {
		this.transcriptionWordCount = transcriptionNoWords;
	}
	public int getTranslationWordCount() {
		return translationWordCount;
	}
	public void setTranslationWordCount(int translationNoWords) {
		this.translationWordCount = translationNoWords;
	}

}

package com.mp.ttapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;
import com.mp.ttapi.dto.FileTranslationDTO;
import com.mp.ttapi.dto.ImageChecksumDTO;
import com.mp.ttapi.service.FileTranslationService;
import com.mp.ttapi.service.ImageTranscriptionService;
import com.mp.ttapi.service.ImageTranslationService;


@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private FileTranslationService fileTranslationService;
	@Autowired
	private ImageTranscriptionService imageTranscriptionService;
	@Autowired
	private ImageTranslationService imageTranslationService;

	@ResponseBody
	@RequestMapping("/ft/all")
	public List<FileTranslationDTO> fileTranslations() {
	    return fileTranslationService.getAllFileTranslationsForDisplay();
	}
	
	@ResponseBody
	@RequestMapping("/ft/start/{startRow}/end/{endRow}")
	public List<FileTranslationDTO> specifiedFileTranslation(@PathVariable("startRow") int start, @PathVariable("endRow") int stop) {
	    return fileTranslationService.getFileTranslationsByRow(start,stop);
	}
	
	@ResponseBody
	@RequestMapping("/ic/get/transription/translation/{checksumId}")
	public ImageChecksumDTO specifiedFileTranslation(@PathVariable("checksumId") int checksumId) {
	    return fileTranslationService.getImageChecksumDto(checksumId);
	}
	
	@ResponseBody
    @RequestMapping(value = "/ic/create/{checksum}")
    public boolean createImageChecksum(@PathVariable("checksum") int checksum){
    	return fileTranslationService.createImageChecksum(checksum);
    }
	
	@ResponseBody
    @RequestMapping(value = "/ft/create/{checksum}/{originUrl}")
    public boolean createFileTranslation(@PathVariable("checksum") int checksum, @PathVariable("originUrl") String originUrl){
    	return fileTranslationService.createFileTranslation(checksum, "http://test.com");
    }
	
	@ResponseBody
	@RequestMapping(value = "/transcription/create/", method = RequestMethod.POST)
	public ImageChecksumDTO createImageTranscription(@RequestBody ImageTranscription it){
		return imageTranscriptionService.createImageTranscription(it.getImageChecksum().getId(), it.getTranscriptionText());
	}
	
	@ResponseBody
	@RequestMapping(value = "/translation/create/", method = RequestMethod.POST)
	public ImageChecksumDTO createImageTranslation(@RequestBody ImageTranslation it){
		return imageTranslationService.createImageTranslation(it.getImageTranscription().getId(), it.getTranslationText());
	}
}

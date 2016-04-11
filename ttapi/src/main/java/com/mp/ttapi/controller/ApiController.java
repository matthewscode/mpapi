package com.mp.ttapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;
import com.mp.ttapi.dto.FileTranslationDTO;
import com.mp.ttapi.dto.ImageChecksumDTO;
import com.mp.ttapi.security.TokenService;
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
	@Autowired
	private TokenService tokenService;

	@ResponseBody
	@RequestMapping("/ft/all")
	public List<FileTranslationDTO> fileTranslations(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
			return fileTranslationService.getAllFileTranslationsForDisplay();
	}
	
	@ResponseBody
	@RequestMapping("/ft/start/{startRow}/end/{endRow}")
	public List<FileTranslationDTO> specifiedFileTranslation(@PathVariable("startRow") int start, @PathVariable("endRow") int stop, @RequestParam("token") String token) {
		if(tokenService.isTokenValid(token)){
			return fileTranslationService.getFileTranslationsByRow(start,stop);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/ic/get/transription/translation/{checksumId}")
	public ImageChecksumDTO specifiedFileTranslation(@PathVariable("checksumId") int checksumId, @RequestParam("token") String token) {
		if(tokenService.isTokenValid(token)){
			return fileTranslationService.getImageChecksumDto(checksumId);
		}
		return null;
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
	public ImageChecksumDTO createImageTranscription(HttpServletRequest request, HttpServletResponse response, @RequestBody ImageTranscription it, @RequestParam("token") String token){
		if(tokenService.isTokenValid(token)){
			return imageTranscriptionService.createImageTranscription(it.getImageChecksum().getId(), it.getTranscriptionText());
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/translation/create/", method = RequestMethod.POST)
	public ImageChecksumDTO createImageTranslation(HttpServletRequest request, HttpServletResponse response, @RequestBody ImageTranslation it, @RequestParam("token") String token){
		if(tokenService.isTokenValid(token)){
			return imageTranslationService.createImageTranslation(it.getImageTranscription().getId(), it.getTranslationText());
		}
		return null;
	}
}

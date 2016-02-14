package com.mp.ttapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file_Translation")
public class FileTranslation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "originUrl")
	private String originUrl;
	
	@ManyToOne
	@JoinColumn(name="image_checksum")
	private ImageChecksum imageChecksum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public ImageChecksum getImageChecksum() {
		return imageChecksum;
	}

	public void setImageChecksum(ImageChecksum imageChecksum) {
		this.imageChecksum = imageChecksum;
	}
	
	
}

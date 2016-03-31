package com.mp.ttapi.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;

@Repository
public class ImageTranscriptionDAOImpl implements ImageTranscriptionDAO{

	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public void addImageTranscription(ImageTranscription it) {
		sessionFactory.getCurrentSession().saveOrUpdate(it);
	}
	
	@Override
	public ImageTranscription getImageTranscription(int id){
		return (ImageTranscription) sessionFactory.getCurrentSession().get(ImageTranscription.class, id);
	}

	@Override
	public ImageTranscription getImageTranscriptionByChecksumId(ImageChecksum ic) {
		return (ImageTranscription) sessionFactory.getCurrentSession().createCriteria(ImageTranscription.class).add(Restrictions.eq("imageChecksum", ic)).uniqueResult();

	}
}

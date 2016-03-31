package com.mp.ttapi.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;

@Repository
public class ImageTranslationDAOImpl implements ImageTranslationDAO{

	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public void addImageTranslation(ImageTranslation it) {
		sessionFactory.getCurrentSession().saveOrUpdate(it);
	}

	@Override
	public ImageTranslation getImageTranslation(int id) {
		return (ImageTranslation) sessionFactory.getCurrentSession().get(ImageTranslation.class, id);
	}

	@Override
	public ImageTranslation getImageTranslationByTranscription(ImageTranscription it) {
		return (ImageTranslation) sessionFactory.getCurrentSession().createCriteria(ImageTranslation.class).add(Restrictions.eq("imageTranscription", it)).uniqueResult();
	}
}

package com.mp.ttapi.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;

@Repository("fileTranslationDAO")
public class FileTranslationDAOImpl implements FileTranslationDAO{

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<FileTranslation> getAllFileTranslations() {
        return sessionFactory.getCurrentSession().createCriteria(FileTranslation.class).list();
    }

	@Override
	public boolean createImageChecksum(ImageChecksum ic) {
		sessionFactory.getCurrentSession().saveOrUpdate(ic);
		return true;
	}

	@Override
	public ImageChecksum getImageChecksumByChecksum(int checksum) {
		return (ImageChecksum) sessionFactory.getCurrentSession().createCriteria(ImageChecksum.class).add(Restrictions.eq("checksum", checksum)).uniqueResult();
	}

	@Override
	public boolean createFileTranslation(FileTranslation ft) {
			sessionFactory.getCurrentSession().saveOrUpdate(ft);
			return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileTranslation> getFileTranslationsByRow(int start, int stop) {
		return sessionFactory.getCurrentSession().createCriteria(FileTranslation.class).setFirstResult(start).setMaxResults(stop-start).list();
	}

	@Override
	public ImageChecksum getImageChecksum(int id) {
		return (ImageChecksum) sessionFactory.getCurrentSession().get(ImageChecksum.class, id);
	}


}

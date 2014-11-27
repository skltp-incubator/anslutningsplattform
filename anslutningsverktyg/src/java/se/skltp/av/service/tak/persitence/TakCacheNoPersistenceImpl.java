package se.skltp.av.service.tak.persitence;

import java.util.ArrayList;
import java.util.List;

import se.skltp.av.service.tak.m.PersistenceEntity;

public class TakCacheNoPersistenceImpl implements TakCachePersistenceServices {

	@Override
	public void persistEndpoints(List<PersistenceEntity> entitys) {
	}

	@Override
	public List<PersistenceEntity> getEndpoints() {
		return new ArrayList<PersistenceEntity>();
	}

}

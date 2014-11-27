package se.skltp.av.service.tak.persitence;

import java.util.List;

import se.skltp.av.service.tak.m.PersistenceEntity;

public interface TakCachePersistenceServices {
	public void persistEndpoints(final List<PersistenceEntity> entitys);
	public List<PersistenceEntity> getEndpoints();
	public PersistenceEntity getEndpoint(final String endpoint);
}

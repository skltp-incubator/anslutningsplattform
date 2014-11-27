package se.skltp.av.service.tak;

import java.util.List;

import se.skltp.av.service.tak.m.PersistenceEntity;
import se.skltp.av.service.tak.persitence.TakCachePersistenceServices;


public interface TakSyncCacheCallback {
	public void onSyncComplete(final List<PersistenceEntity> persitencesEntitys);
	public void onSyncSuccess(final String endpoint);
	public void onSyncError(final String endpoint, final Exception err);
}

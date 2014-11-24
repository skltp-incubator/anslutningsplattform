package se.skltp.av.service.tak;

import java.util.Date;

public interface TakCache {
	public void put(final Object obj);
	public Object get(final Object obj);
	public Date lastSynched();
}

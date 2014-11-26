package se.skltp.av.service.tak;

public interface TakCacheCallback {
	public void onSuccess(final String endpoint);
	public void onError(final String endpoint, final Exception err);
}

package se.skltp.av.services.dto

class TakRoutingEntryDTO {
    String id
    String name
	String url
	
	String toString() {
		StringBuilder sb = new StringBuilder()
		sb.append(getClass().getSimpleName())
		sb.append(" [id: ")
		sb.append(id)
		sb.append(", name: ")
		sb.append(name)
		sb.append(", url: ")
		sb.append(url)
		sb.append("]")
	}
}

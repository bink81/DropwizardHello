package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionToListConverter<T> {

	private final Collection<T> collection;

	public CollectionToListConverter(Collection<T> collection) {
		this.collection = collection;
	}

	public List<T> convert() {
		List<T> list;
		if (collection instanceof List)
			list = (List<T>) collection;
		else
			list = new ArrayList<T>(collection);
		return list;
	}
}

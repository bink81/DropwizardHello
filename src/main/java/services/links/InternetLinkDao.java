package services.links;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public enum InternetLinkDao {
	instance;

	// simple database (key = InternetLink.getId)
	private final Map<String, InternetLink> internetLinks = new HashMap<String, InternetLink>();

	private InternetLinkDao() {
		internetLinks.put("1", new InternetLink("1", "A", "Link1", InternetLinkType.ARTICLE));
		internetLinks.put("2", new InternetLink("2", "B", "Link2", InternetLinkType.NEWS));
		internetLinks.put("3", new InternetLink("3", "A", "Link3", InternetLinkType.SERVICE));
	}

	public InternetLink getInternetLinkById(String id) {
		InternetLink internetLink = internetLinks.get(id);
		if (internetLink == null) {
			throw new RuntimeException("InternetLink resource not found for id: " + id);
		}
		return internetLink;
	}

	public Collection<InternetLink> getInternetLinksByName(String name) {
		if (StringUtils.isBlank(name)) {
			return internetLinks.values();
		}
		Collection<InternetLink> res = new ArrayList<InternetLink>();
		for (InternetLink internetLink : internetLinks.values()) {
			if (internetLink.getName().equals(name)) {
				res.add(internetLink);
			}
		}
		return res;
	}

	public Collection<InternetLink> getAllInternetLinks() {
		return internetLinks.values();
	}

	public String put(InternetLink internetLink) {
		internetLinks.put(internetLink.getId(), internetLink);
		return internetLink.getId();
	}

	public InternetLink delete(String id) {
		getInternetLinkById(id); // check if exists
		InternetLink InternetLinkById = internetLinks.remove(id);
		return InternetLinkById;
	}

	public boolean containsId(String id) {
		return internetLinks.containsKey(id);
	}

	public int getInternetLinkCount() {
		return internetLinks.size();
	}
}

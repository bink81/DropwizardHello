package services.links;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public enum InternetLinkDao {
	instance;

	// simple database (key = InternetLink.getId)
	private final Map<String, InternetLink> internetLinks = new HashMap<String, InternetLink>();

	private InternetLinkDao() {
		putArticleLink("1", "A", "Link1", InternetLinkType.ARTICLE);
		putArticleLink("2", "B", "Link2", InternetLinkType.NEWS);
		putArticleLink("3", "A", "Link3", InternetLinkType.SERVICE);
	}

	private void putArticleLink(String id, String name, String url, InternetLinkType type) {
		InternetLink internetLink = new InternetLink(id, name, url, type);
		internetLinks.put(id, internetLink);
	}

	public InternetLink getInternetLinkById(String id) {
		InternetLink internetLink = internetLinks.get(id);
		if (internetLink == null) {
			throw new RuntimeException("InternetLink resource not found for id: " + id);
		}
		return internetLink;
	}

	public Collection<InternetLink> getInternetLinksByName(String name) {
		Collection<InternetLink> allLinks = internetLinks.values();
		if (StringUtils.isBlank(name)) {
			return allLinks;
		}
		Collection<InternetLink> res = groupByName(allLinks).get(name);
		if (res == null) {
			return Collections.emptyList();
		}
		return res;
	}

	private Map<String, List<InternetLink>> groupByName(Collection<InternetLink> allLinks) {
		Map<String, List<InternetLink>> linksByName = allLinks.stream()
				.collect(groupingBy(InternetLink::getName));
		return linksByName;
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

	public Collection<InternetLink> getInternetLinksByTypeAndName(InternetLinkType type,
			String name) {
		Preconditions.checkNotNull(type, "type must not be null!");
		Preconditions.checkNotNull(name, "name must not be null!");
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("name must not be empty!");
		}

		Map<InternetLinkType, Map<String, List<InternetLink>>> allTypes = groupByTypeAndName(
				internetLinks.values());
		List<InternetLink> res = allTypes.get(type).get(name);
		if (res == null) {
			res = Collections.emptyList();
		}
		return res;
	}

	private Map<InternetLinkType, Map<String, List<InternetLink>>> groupByTypeAndName(
			Collection<InternetLink> allLinks) {
		Map<InternetLinkType, Map<String, List<InternetLink>>> linksByTypeAndName = allLinks.stream()
				.collect(groupingBy(InternetLink::getType, groupingBy(InternetLink::getName)));
		return linksByTypeAndName;
	}
}

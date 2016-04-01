package services.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public enum ArticleLinkDao {
	instance;

	// simple database (key = ArticleLink.getId)
	private final Map<String, ArticleLink> articleLinks = new HashMap<String, ArticleLink>();

	private ArticleLinkDao() {
		articleLinks.put("1", new ArticleLink("1", "A", "Link1"));
		articleLinks.put("2", new ArticleLink("2", "B", "Link2"));
		articleLinks.put("3", new ArticleLink("3", "A", "Link3"));
	}

	public ArticleLink getArticleLinkById(String id) {
		ArticleLink articleLink = articleLinks.get(id);
		if (articleLink == null) {
			throw new RuntimeException("ArticleLink resource not found for id: " + id);
		}
		return articleLink;
	}

	public Collection<ArticleLink> getArticleLinksByName(String name) {
		if (StringUtils.isBlank(name)) {
			return articleLinks.values();
		}
		Collection<ArticleLink> res = new ArrayList<ArticleLink>();
		for (ArticleLink articleLink : articleLinks.values()) {
			if (articleLink.getName().equals(name)) {
				res.add(articleLink);
			}
		}
		return res;
	}

	public Collection<ArticleLink> getAllArticleLinks() {
		return articleLinks.values();
	}

	public String put(ArticleLink articleLink) {
		articleLinks.put(articleLink.getId(), articleLink);
		return articleLink.getId();
	}

	public ArticleLink delete(String id) {
		getArticleLinkById(id); // check if exists
		ArticleLink articleLinkById = articleLinks.remove(id);
		return articleLinkById;
	}

	public boolean containsId(String id) {
		return articleLinks.containsKey(id);
	}

	public int getArticleLinkCount() {
		return articleLinks.size();
	}
}

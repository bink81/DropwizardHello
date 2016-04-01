package services.users;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {

	private final ArticleLinkDao articleLinkDao;

	public DatabaseHealthCheck(ArticleLinkDao articleLinkDao) {
		this.articleLinkDao = articleLinkDao;
	}

	@Override
	protected Result check() throws Exception {
		// This is a dummy operation to check whether the data is healthy.
		// If we had a database, we would check the connection.
		int allArticleLinksFilterCount = articleLinkDao.getAllArticleLinks().size();
		int allArticleLinksCount = articleLinkDao.getArticleLinkCount();
		if (allArticleLinksCount != allArticleLinksFilterCount) {
			Result.unhealthy("Count of data does not match");
		}
		return Result.healthy();
	}

}
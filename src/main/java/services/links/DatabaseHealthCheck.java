package services.links;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {

	private final InternetLinkDao internetLinkDao;

	public DatabaseHealthCheck(InternetLinkDao internetLinkDao) {
		this.internetLinkDao = internetLinkDao;
	}

	@Override
	protected Result check() throws Exception {
		// This is a dummy operation to check whether the data is healthy.
		// If we had a database, we would check the connection.
		int allInternetLinksFilterCount = internetLinkDao.getAllInternetLinks().size();
		int allInternetLinksCount = internetLinkDao.getInternetLinkCount();
		if (allInternetLinksCount != allInternetLinksFilterCount) {
			Result.unhealthy("Count of data does not match");
		}
		return Result.healthy();
	}

}
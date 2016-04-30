package services.links;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InternetLinkDaoTest {

	private static final String DUMMY_NAME = "A";
	private InternetLinkDao internetLinkDao;

	@Before
	public void setUp() throws Exception {
		internetLinkDao = InternetLinkDao.instance;
	}

	@Test
	public void testCount() throws Exception {
		Integer actualCount = internetLinkDao.getInternetLinkCount();

		Assert.assertEquals((Integer) 3, actualCount);
	}

	@Test
	public void testGetByNameA() throws Exception {
		Collection<InternetLink> linksWithNameA = internetLinkDao.getInternetLinksByName(DUMMY_NAME);

		Assert.assertEquals(2, linksWithNameA.size());
	}

	@Test
	public void testGetByNameB() throws Exception {
		Collection<InternetLink> linksWithNameA = internetLinkDao.getInternetLinksByName("B");

		Assert.assertEquals(1, linksWithNameA.size());
	}

	@Test
	public void testGetByNameX() throws Exception {
		Collection<InternetLink> linksWithNameA = internetLinkDao.getInternetLinksByName("X");

		Assert.assertEquals(0, linksWithNameA.size());
	}

	@Test
	public void testGetByNameNull() throws Exception {
		Collection<InternetLink> linksWithNameA = internetLinkDao.getInternetLinksByName(null);

		Assert.assertEquals(3, linksWithNameA.size());
	}

	@Test
	public void testGetByNameFilterEmpty() throws Exception {
		Collection<InternetLink> linksWithNameA = internetLinkDao.getInternetLinksByName("");

		Assert.assertEquals(3, linksWithNameA.size());
	}

	@Test
	public void testGetByTypeArticleAndNameWithArticleAndA() throws Exception {
		Collection<InternetLink> linksWithNameA = internetLinkDao
				.getInternetLinksByTypeAndName(InternetLinkType.ARTICLE, DUMMY_NAME);

		Assert.assertEquals(1, linksWithNameA.size());
	}

	@Test(expected = NullPointerException.class)
	public void testGetByTypeArticleAndNameWithNullArticleAndName() throws Exception {
		internetLinkDao
				.getInternetLinksByTypeAndName(null, DUMMY_NAME);
	}

	@Test(expected = NullPointerException.class)
	public void testGetByTypeArticleAndNameWithArticleAndNullName() throws Exception {
		internetLinkDao
				.getInternetLinksByTypeAndName(InternetLinkType.ARTICLE, null);
	}

	@Test(expected = IllegalStateException.class)
	public void testGetByTypeArticleAndNameWithArticleAndEmptyName() throws Exception {
		internetLinkDao
				.getInternetLinksByTypeAndName(InternetLinkType.ARTICLE, "");
	}
}

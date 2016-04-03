package services;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.quotes.Quote;
import services.quotes.QuoteDao;

public class FavouriteQuoteImporterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadFile() throws Exception {
		QuoteDao quoteDao = QuoteDao.instance;
		new FavouriteQuoteImporter(quoteDao).loadFile();
		Collection<Quote> allQuotes = quoteDao.getAllQuotes();

		Assert.assertFalse(allQuotes.isEmpty());
	}
}

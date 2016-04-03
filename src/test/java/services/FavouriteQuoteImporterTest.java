package services;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import services.quotes.Quote;
import services.quotes.QuoteDao;

//This is really an integration test and requires a running service
public class FavouriteQuoteImporterTest {

	@Test
	public void testLoadFile() throws Exception {
		QuoteDao quoteDao = QuoteDao.instance;
		new FavouriteQuoteImporter(quoteDao).loadFile();
		Collection<Quote> allQuotes = quoteDao.getAllQuotes();

		Assert.assertFalse(allQuotes.isEmpty());
	}
}

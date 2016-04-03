package services;

import services.quotes.QuoteDao;

public class QuoteImporter {

	final private QuoteDao quoteDao;

	public QuoteImporter(QuoteDao quoteDao) {
		this.quoteDao = quoteDao;
	}

}

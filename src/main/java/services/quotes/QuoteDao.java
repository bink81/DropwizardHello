package services.quotes;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.CollectionToListConverter;

public enum QuoteDao {
	instance;
	private static final int MAX_LIMIT_FOR_AUTHOR = 200;

	private static final int MAX_LIMIT_FOR_TEXT = 2000;

	private static final Logger LOGGER = LoggerFactory.getLogger(QuoteDao.class);

	// simple database (key = Quote.getId)
	private final Map<String, Quote> quotes = new HashMap<String, Quote>();

	public void insertQuote(Quote quote) {
		warnOfSuspiciouslyLongString(quote.getText(), MAX_LIMIT_FOR_TEXT);
		warnOfSuspiciouslyLongString(quote.getAuthor(), MAX_LIMIT_FOR_AUTHOR);
		quotes.put(quote.getId(), quote);
	}

	private void warnOfSuspiciouslyLongString(String string, int maxLimit) {
		if (string != null && string.length() > maxLimit) {
			LOGGER.warn("string is suspiciously long: {}", string);
		}
	}

	public Quote getQuoteById(String id) {
		Quote internetLink = quotes.get(id);
		if (internetLink == null) {
			throw new RuntimeException("Quote resource not found for id: " + id);
		}
		return internetLink;
	}

	public boolean containsId(String id) {
		return quotes.containsKey(id);
	}

	public int getQuoteCount() {
		return quotes.size();
	}

	public Collection<Quote> getAllQuotes() {
		return quotes.values();
	}

	public Quote getRandomQuote() {
		List<Quote> list = new CollectionToListConverter<Quote>(quotes.values()).convert();
		return list.get((int) (Math.random() * list.size()));
	}

	public List<Quote> getQuotesByAuthor(String author) {
		Map<String, List<Quote>> quotesByAuthor = quotes.values().stream()
				.collect(groupingBy(Quote::getAuthor));
		return quotesByAuthor.get(author);
	}
}

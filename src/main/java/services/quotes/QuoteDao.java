package services.quotes;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.CollectionToListConverter;

public enum QuoteDao {
	instance;

	// simple database (key = Quote.getId)
	private final Map<String, Quote> quotes = new HashMap<String, Quote>();

	private QuoteDao() {
		putArticleLink("1", 1, "Text1", "A");
		putArticleLink("2", 2, "Text2", "B");
		putArticleLink("3", 3, "Text3", "A");
		putArticleLink("4", 4, "Text4", "C");
		putArticleLink("5", 5, "Text5", "D");
	}

	private void putArticleLink(String id, Integer episodeNumber, String text, String author) {
		Quote internetLink = new Quote(id, episodeNumber, text, author);
		quotes.put(id, internetLink);
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

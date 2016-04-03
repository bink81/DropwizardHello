package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import services.quotes.Quote;
import services.quotes.QuoteDao;
import utils.LineByLineFileReader;

public class FavouriteQuoteImporter extends LineByLineFileReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuoteDao.class);

	private static final String AUTHOR_DELIMETER = "#";
	private static final Pattern PATTERN = Pattern.compile("(\\d+\\s+)?(.+)");
	private final QuoteDao quoteDao;

	public FavouriteQuoteImporter(QuoteDao quoteDao) {
		super("quotes/favouriteQuotes.txt");
		this.quoteDao = quoteDao;
	}

	@Override
	public void parseLine(String line) {
		LOGGER.debug("Parsing line: {}" + line);
		if (line.trim().isEmpty()) {
			return;
		}
		Matcher matcher = PATTERN.matcher(line);
		while (matcher.find()) {
			Quote quote = assembleQuote(matcher);
			quoteDao.insertQuote(quote);
			return;
		}
	}

	private Quote assembleQuote(final Matcher matcher) {
		String episodeNumberString = matcher.group(1);
		// assign default values so as not to break stream filtering in QuoteDao
		Integer episodeNumber = episodeNumberString != null
				? Integer.valueOf(episodeNumberString.trim()) : 0;
		String textWithAuthor = matcher.group(2).trim();
		String author = "";
		String text = textWithAuthor;
		if (textWithAuthor.contains(AUTHOR_DELIMETER)) {
			author = textWithAuthor.substring(textWithAuthor.indexOf(AUTHOR_DELIMETER) + 1).trim();
			text = textWithAuthor.substring(0, textWithAuthor.indexOf(AUTHOR_DELIMETER)).trim();
		}
		return new Quote(episodeNumber, text, author);
	}
}

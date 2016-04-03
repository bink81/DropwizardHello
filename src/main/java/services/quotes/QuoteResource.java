package services.quotes;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(value = "/quotes")
public class QuoteResource {

	private QuoteDao quoteDao;

	public QuoteResource(QuoteDao quoteDao) {
		this.quoteDao = quoteDao;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(quoteDao.getQuoteCount());
	}

	@GET
	@Path("{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Quote getInternetLinkById(@PathParam("id") String id) {
		return quoteDao.getQuoteById(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Quote> getQuotes() {
		return quoteDao.getAllQuotes();
	}

	@GET
	@Path("random")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces(MediaType.TEXT_PLAIN)
	public Quote getRandom() {
		return quoteDao.getRandomQuote();
	}

	@GET
	@Path("random")
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		String response = quoteDao.getRandomQuote().getText();
		return "<?xml version=\"1.0\"?>" + "<hello> " + response + "</hello>";
	}

	@GET
	@Path("random")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		Quote randomQuote = quoteDao.getRandomQuote();
		return "<html> " + "<title>" + "Hello" + "</title>"
				+ "<body>"
				+ "Quote:<br><h3>" + randomQuote.getText() + "</h3><br>"
				+ "Author:<br><h3>" + randomQuote.getAuthor() + "</h3><br>"
				+ "</body>" + "</html> ";
	}

	@GET
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Quote> sayPlainTextHello(@QueryParam("author") String author) {
		Collection<Quote> quotesForAuthor = quoteDao.getQuotesByAuthor(author);
		return quotesForAuthor;
	}
}

package services.quotes;

import java.util.UUID;

public class Quote {
	private final String id = UUID.randomUUID().toString();
	private final Integer episodeNumber;
	private final String text;
	private final String author;

	public Quote(Integer episodeNumber, String text, String author) {
		super();
		this.episodeNumber = episodeNumber;
		this.text = text;
		this.author = author;
	}

	public final String getId() {
		return id;
	}

	public final Integer getEpisodeNumber() {
		return episodeNumber;
	}

	public final String getText() {
		return text;
	}

	public final String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote [id=" + id);
		builder.append(", episodeNumber=" + episodeNumber);
		builder.append(", text=" + text);
		builder.append(", author=" + author);
		return builder.append("]").toString();
	}
}

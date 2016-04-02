package services.links;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@XmlRootElement // only to generate XML
public class InternetLink {
	@NotBlank
	private String id;

	@NotBlank
	private String name;

	@URL
	@NotBlank
	private String url;

	@NotBlank
	private InternetLinkType type;

	private final Date modifiedOn = new Date();

	public InternetLink() {
	}

	public InternetLink(String id, String name, String url, InternetLinkType type) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.setType(type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InternetLink [id=" + id);
		builder.append(", name=" + name);
		builder.append(", url=" + url);
		builder.append(", type=" + getType());
		builder.append(", modifiedOn=" + modifiedOn);
		return builder.append("]").toString();
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

	public final Date getModifiedOn() {
		return modifiedOn;
	}

	public InternetLinkType getType() {
		return type;
	}

	public void setType(InternetLinkType type) {
		this.type = type;
	}
}

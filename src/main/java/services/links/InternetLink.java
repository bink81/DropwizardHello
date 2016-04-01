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

	private final Date modifiedOn = new Date();

	public InternetLink() {
	}

	public InternetLink(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	@Override
	public String toString() {
		return "InternetLink [id=" + id + ", name=" + name + ", url=" + url + ", createdOn="
				+ modifiedOn + "]";
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
}

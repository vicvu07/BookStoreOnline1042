package application.data.model;

import javax.persistence.*;


@Entity(name = "dbo_banner")
public class Banner {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "banner_id")
    @Id
    private int id;

    @Column(name = "link")
    private String link;

    @Column(name = "alt_text")
    private String altText;

    @Column (name = "image_url")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package articlejar;

import java.io.Serializable;

/**
 *
 * @author thoma
 */
public class Article implements Serializable {

    private String headline;
    private String bodyText;

    public Article(String headline, String bodyText) {
        this.headline = headline;
        this.bodyText = bodyText;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBodyText() {
        return bodyText;
    }

}

package articlejar;

import java.io.Serializable;

/**
 *
 * @author thoma
 */
public class Article implements Serializable {

    private String headline;
    private String bodyText;
    private int id;

    public Article(String headline, String bodyText, int id) {
        this.headline = headline;
        this.bodyText = bodyText;
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBodyText() {
        return bodyText;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        String s = headline;
        s += "\n" + bodyText;
        return s;
    }
}

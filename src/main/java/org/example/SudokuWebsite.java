package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class SudokuWebsite {
    String identifier;
    private final String userAgentString = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0";

    public Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(userAgentString)
                .header("Accept-Language", "*")
                .get();
        return doc;
    }

    public abstract String getImportString(int difficulty);
    public abstract String getWebsiteURL(int difficulty);
    public String getIdentifier() {
        return identifier;
    };

}

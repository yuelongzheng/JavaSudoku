package com.example.javasudoku;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class SudokuWebsite {
    String date;
    String sourceURL;
    String[] diffArray;

    public Document getDocument(String url) throws IOException {
        String userAgentArg = "Mozilla/5.0 (Macintosh; Intel Mac " +
                "OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0";
        return Jsoup.connect(url)
                .userAgent(userAgentArg)
                .header("Accept-Language", "*")
                .get();
    }

    public abstract String getImportString(String difficulty);
    public abstract String getWebsiteURL(String difficulty);

}

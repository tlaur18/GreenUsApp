package com.example.greenusapp.articles;

import java.util.ArrayList;
import java.util.List;

import articlejar.Article;

public class ArticleList {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Article> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
//    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static void addItem(Article article) {
        ITEMS.add(article);
//        ITEM_MAP.put(article.id, article);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}

package ru.nvaleyev.sd.refactoring.html;

public class HtmlUtils {
    public final static String BODY_HEADER = "<html><body>";
    public final static String BODY_FOOTER = "</body></html>";
    public final static String BR_TAG = "</br>";
    public final static String H1_OPEN = "<h1>";
    public final static String H1_CLOSE = "</h1>";
    public final static String CONTENT_TYPE = "text/html";

    public static String makeHeader(String str) {
        return H1_OPEN + str + H1_CLOSE;
    }

    public static String withBr(String str) {
        return str + BR_TAG;
    }

    public static String bodyHeader() {
        return BODY_HEADER;
    }

    public static String bodyFooter() {
        return BODY_FOOTER;
    }

    public static String getContentType() {
        return CONTENT_TYPE;
    }
}

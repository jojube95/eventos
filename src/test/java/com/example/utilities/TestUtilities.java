package com.example.utilities;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class TestUtilities {
    public static String getContent(String path) {
        String content = "";
        try {
            content = Files.asCharSource(new File(path), StandardCharsets.UTF_8).read().replaceAll(" ", "").replaceAll("\\r\\n", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String processContent(String content) {
        content = content.replaceAll("<input(?:.*?)name=\"_csrf\"(?:.*?)>", "").replaceAll(" ", "").replaceAll("\\r\\n", "");
        return content;
    }
}

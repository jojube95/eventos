package com.example.utilities;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class TestUtilities {
    public static String getContent(String path) {
        String content = "";
        try {
            content = Files.asCharSource(new File(path), StandardCharsets.UTF_8).read().replaceAll(" ", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

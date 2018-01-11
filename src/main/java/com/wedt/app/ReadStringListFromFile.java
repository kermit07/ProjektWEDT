package com.wedt.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadStringListFromFile {

    public static List<String> getDict(String fileName) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(Config.PROJECT_PATH + fileName));
        return stream.collect(Collectors.toList());
    }
}

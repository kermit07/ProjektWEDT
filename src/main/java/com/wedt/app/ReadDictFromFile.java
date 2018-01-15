package com.wedt.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadDictFromFile {

    // w każdej linii może być wiele słów (grupa)

    public static List<Set<String>> getDict(String fileName) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(Config.PROJECT_PATH + fileName));
        List<Set<String>> result = new ArrayList<>();
        stream.forEach(line -> result.add(Arrays.stream(line.split(",")).collect(Collectors.toSet())));
        return result;
    }
}

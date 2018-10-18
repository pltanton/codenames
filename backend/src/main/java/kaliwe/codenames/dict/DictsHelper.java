package kaliwe.codenames.dict;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class DictsHelper {
    private final static String DICTS_BASE_DIR_PATH = "dicts";
    private final static File DICTS_DIR = new File(
            DictsHelper.class.getClassLoader().getResource(DICTS_BASE_DIR_PATH).getFile()
    );

    List<String> readDict(String dictName) throws FileNotFoundException {
        File dictFile = new File(DICTS_DIR, dictName);
        try (Scanner scnr = new Scanner(dictFile)) {
            return Arrays.asList(scnr.next().split(","));
        }
    }

    List<String> allDicts() {
        return dictsFromResources().stream().flatMap((String dictName) -> {
            List<String> result;
            try {
                result = readDict(dictName);
            } catch (FileNotFoundException e) {
                result = Collections.emptyList();
            }
            return result.stream();
        }).collect(Collectors.toList());
    }

    List<String> dictsFromResources() {
        return Arrays.stream(DICTS_DIR.listFiles()).map(File::getName).collect(Collectors.toList());
    }
}

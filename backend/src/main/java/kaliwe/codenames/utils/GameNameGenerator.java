package kaliwe.codenames.utils;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class GameNameGenerator {
    private final int minLength;
    private final int maxLength;

    private static final Map<Character, Map<Character, Double>> PROP_MAP = readMap();

    private static Map<Character, Map<Character, Double>> readMap() {
        Map<Character, Map<Character, Double>> result = new HashMap<>();
        File propTable = new File(GameNameGenerator.class.getClassLoader().getResource("prop_table.txt").getFile());
        try {
            Scanner scanner = new Scanner(propTable);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                char cur = line[0].charAt(0);
                char nxt = line[1].charAt(0);
                double prop = Double.parseDouble(line[2]);

                if (!result.containsKey(cur)) result.put(cur, new HashMap<>());
                result.get(cur).put(nxt, prop);
            }
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }

    private char getNext(char cur) {
        System.out.println((int)cur);
        Map<Character, Double> props = PROP_MAP.get(cur);
        double p = Math.random();
        double cumulativeProbability = 0.0;

        for (Map.Entry<Character, Double> entry : props.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (p <= cumulativeProbability) return entry.getKey();
        }
        return 'а';
    }

    public String generate() {
        int length = minLength + (int)(Math.random() * ((maxLength - minLength) + 1));
        StringBuilder sb = new StringBuilder();
        char cur = (char)('а' + (int)(Math.random() * (('я' - 'а') + 1)));
        for (int i = 0; i < length; i++) {
            sb.append(cur);
            cur = getNext(cur);
        }
        return sb.toString();
    }
}

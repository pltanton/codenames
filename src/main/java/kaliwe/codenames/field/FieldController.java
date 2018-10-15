package kaliwe.codenames.field;

import javassist.tools.web.BadHttpRequest;
import kaliwe.codenames.cell.Cell;
import kaliwe.codenames.cell.CellColor;
import kaliwe.codenames.cell.CellId;
import kaliwe.codenames.utils.GameNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FieldController {
    private final FieldRepository fieldRepository;
    private final CellRepository cellRepository;
    private final static GameNameGenerator nameGenerator = new GameNameGenerator(3, 7);

    @RequestMapping(value = "/field", method = GET)
    public Field getField(@RequestParam String id) throws Exception {
        return fieldRepository.findById(id).orElseThrow(Exception::new);
    }

    @RequestMapping(value = "/field", method = POST)
    public String createField(@RequestParam String wordsString) throws Exception {

        List<String> words = generateWordsMap(wordsString);
        List<CellColor> colors = generateColorsMap();
        Field field = createNewGame();

        createCells(field, words, colors);
        return field.getId();
    }

    private List<Cell> createCells(Field field, List<String> words, List<CellColor> colors) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Cell cell = new Cell();
            cell.setColor(colors.get(i));
            cell.setWord(words.get(i));
            cell.setOpen(false);
            cell.setId(new CellId(field.getId(), i));
            cells.add(cell);
        }
        return cellRepository.saveAll(cells);
    }

    private List<String> generateWordsMap(String wordsString) throws Exception {
        List<String> words = Arrays.stream(wordsString.split(",")).map(String::trim).collect(Collectors.toList());
        if (words.size() != 25) throw new Exception();
        Collections.shuffle(words);
        return words;
    }

    private List<CellColor> generateColorsMap() {
        List<CellColor> colors = new ArrayList<>();
        for (int i = 0; i < 8; i ++) {
            colors.add(CellColor.BLUE);
            colors.add(CellColor.RED);
        }
        for (int i = 0; i < 7; i ++) colors.add(CellColor.WHITE);
        colors.add(CellColor.BLACK);
        colors.add(Math.random() > 0.5 ? CellColor.BLUE : CellColor.RED);

        Collections.shuffle(colors);
        return colors;
    }

    private synchronized Field createNewGame() {
        String name;
        do {
            name = nameGenerator.generate();
        } while (fieldRepository.findById(name).isPresent());
        Field field = new Field();
        field.setId(name);
        return fieldRepository.save(field);
    }
}

package kaliwe.codenames.dict;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DictsController {
    private final DictsHelper dictsHelper;

    @GetMapping("/dicts/list")
    public List<String> listDicts() {
        return dictsHelper.dictsFromResources();
    }

    @GetMapping("/dicts/all")
    public List<String> gitAllDicts() {
        return dictsHelper.allDicts();
    }

    @GetMapping("/dict/{dictName}")
    public ResponseEntity getDict(@PathVariable String dictName) {
        try {
            return ResponseEntity.ok(dictsHelper.readDict(dictName));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

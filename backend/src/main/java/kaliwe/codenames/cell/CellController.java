package kaliwe.codenames.cell;

import kaliwe.codenames.field.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CellController {
    private final CellRepository cellRepository;

    @RequestMapping(value = "/field/{fieldId}/cell/{position}/open", method = RequestMethod.PUT)
    public ResponseEntity openCell(@PathVariable String fieldId, @PathVariable String position) {
        Optional<Cell> cellOptional= cellRepository.findById(new CellId(fieldId, Integer.parseInt(position)));
        if (!cellOptional.isPresent()) return ResponseEntity.notFound().build();
        Cell cell = cellOptional.get();
        cell.setOpen(true);
        cellRepository.save(cell);
        return ResponseEntity.ok().build();
    }
}

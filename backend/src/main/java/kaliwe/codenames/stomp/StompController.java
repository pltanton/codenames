package kaliwe.codenames.stomp;

import kaliwe.codenames.cell.Cell;
import kaliwe.codenames.cell.CellId;
import kaliwe.codenames.cell.CellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StompController {
    private final CellRepository cellRepository;

    @MessageMapping("/foo")
    @SendTo("/topic/bar")
    public int openCell(int position) {
        Optional<Cell> cellOptional= cellRepository.findById(new CellId("хостоги", position));
        if (!cellOptional.isPresent()) return -1;
        Cell cell = cellOptional.get();
        cell.setOpen(true);
        cellRepository.save(cell);
        return position;
    }

}

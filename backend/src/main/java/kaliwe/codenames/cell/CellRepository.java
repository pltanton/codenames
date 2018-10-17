package kaliwe.codenames.cell;

import kaliwe.codenames.cell.Cell;
import kaliwe.codenames.cell.CellId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository<Cell, CellId> {
}

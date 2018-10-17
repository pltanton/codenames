package kaliwe.codenames.cell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kaliwe.codenames.field.Field;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CellId implements Serializable {
    @JsonIgnore
    @Column(nullable = false, name = "field_id")
    private String fieldId;

    @Column(nullable = false)
    private Integer position;
}

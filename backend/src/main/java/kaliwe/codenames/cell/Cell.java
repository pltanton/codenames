package kaliwe.codenames.cell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kaliwe.codenames.field.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@TypeDef(
        name = "cell_color",
        typeClass = PostgreSQLEnumType.class
)
public class Cell {
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean open;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Type(type = "cell_color")
    private CellColor color;

    @Column(nullable = false)
    private String word;

    @EmbeddedId
    private CellId id;

    @ManyToOne
    @JoinColumn(name="field_id", insertable = false, updatable = false)
    @JsonIgnore
    private Field field;
}

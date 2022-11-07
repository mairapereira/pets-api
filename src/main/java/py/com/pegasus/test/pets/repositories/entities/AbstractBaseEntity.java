package py.com.pegasus.test.pets.repositories.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractBaseEntity {

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

}
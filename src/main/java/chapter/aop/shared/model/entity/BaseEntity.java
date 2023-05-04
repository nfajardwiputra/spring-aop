package chapter.aop.shared.model.entity;

import chapter.aop.shared.constant.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @Length(max = 32)
    @Column(name = TableName.ID)
    private String id;

    @Column(name = TableName.CREATED_DATE, updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.createdDate = LocalDateTime.now();
    }

    @Column(name = TableName.UPDATED_DATE)
    private LocalDateTime updatedDate;

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

}

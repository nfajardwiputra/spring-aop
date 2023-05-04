package chapter.aop.modules.employee.model.entity;

import chapter.aop.shared.constant.TableName;
import chapter.aop.shared.model.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = TableName.EMPLOYEE)
public class Employee extends BaseEntity {

    @Length(max = 100)
    @Column(name = TableName.NAME)
    private String name;

}

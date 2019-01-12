package backend.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
abstract class BaseEntity {

  @Id
  @GeneratedValue
  private Long id;
}

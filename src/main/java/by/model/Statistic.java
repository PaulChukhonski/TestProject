package by.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "statistic")
public class Statistic {
    @Id
    private Long personCount;

    private Long carCount;

    private Long uniqueVendorCount;
}

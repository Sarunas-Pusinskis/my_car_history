package lt.sarunas.Spring_demo.repository.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "my_car_history")
@Data
@NoArgsConstructor
public class CarHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "part_name")
    private String part_Name;
    @Column(name = "quantity")
    private Double quantity ;
    @Column(name = "part_price")
    private Double part_Price;
    @Column(name = "repair_cost")
    private Double repair_Cost;
    @Column(name = "service_name")
    private String service_Name;
    @Column(name = "date")
    private String date;

    public CarHistory(String part_Name) {
        this.part_Name = part_Name;
    }
}

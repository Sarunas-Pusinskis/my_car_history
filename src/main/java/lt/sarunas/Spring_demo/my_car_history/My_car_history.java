package lt.sarunas.Spring_demo.my_car_history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "my_car_history")
@Getter @Setter @NoArgsConstructor @ToString
public class My_car_history {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "part_name")
    private String part_Name;
    @Column(name = "quantity")
    private String quantity ;
    @Column(name = "part_price")
    private String part_Price;
    @Column(name = "repair_cost")
    private String repair_Cost;
    @Column(name = "service_name")
    private String service_Name;
    @Column(name = "date")
    private String date;

}

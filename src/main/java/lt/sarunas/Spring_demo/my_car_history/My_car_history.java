package lt.sarunas.Spring_demo.my_car_history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "my_car_history")
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

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getPart_Name() {return part_Name;}

    public void setPart_Name(String part_Name) {this.part_Name = part_Name;}

    public String getQuantity() {return quantity;}

    public void setQuantity(String quantity) {this.quantity = quantity;}

    public String getPart_Price() {return part_Price;}

    public void setPart_Price(String part_Price) {this.part_Price = part_Price;}

    public String getRepair_Cost() {return repair_Cost;}

    public void setRepair_Cost(String repair_Cost) {this.repair_Cost = repair_Cost;}

    public String getService_Name() {return service_Name;}

    public void setService_Name(String service_Name) {this.service_Name = service_Name;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public My_car_history() {
        super();
    }

    @Override
    public String toString() {
        return "My_car_history{" +
                "id=" + id +
                ", part_Name='" + part_Name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", part_Price='" + part_Price + '\'' +
                ", repair_Cost='" + repair_Cost + '\'' +
                ", service_Name='" + service_Name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

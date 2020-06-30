package client_back1.demo.entity;/*package client_back1.demo.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="image_model")
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ImageModel {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    //Custom Construtor
    public ImageModel(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }
}*/

import client_back1.demo.entity.Product;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "image_table")
//@JsonIgnoreProperties("product")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ImageModel {
    @Id
    @Column(name = "id_img")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@ManyToOne @JoinColumn(name="id", nullable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
   // @JsonManagedReference(value="pro-image")
    //@JsonIgnore
   // @JsonBackReference(value="pro-image")
    private Product product;
 //   private long product_id;
    @OneToMany(
            mappedBy = "productIcon",
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<ProductInOrder> productInOrders= new ArrayList<ProductInOrder>();

    public Product getProduct() {
     return product;
 }

    public void setProduct(Product product) {
        this.product = product;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

 /*   public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }*/

    //private Product product;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "picByte", length = 10000000)
    private byte[] picByte;
    public ImageModel() {
        super();
    }

    public ImageModel(long id) {
        this.id = id;
    }


    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public String getName() {
        return name;

    }
public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public byte[] getPicByte() {
        return picByte;
    }
 public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}

package client_back1.demo.entity;

import client_back1.demo.entity.Cart;
import client_back1.demo.entity.OrderMain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static client_back1.demo.service.ImageService.compressBytes;

@Entity
@Data
//@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  //  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_main")
    @JsonIgnore
    private OrderMain orderMain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image")
   //  @JsonIgnore
    private ImageModel productIcon;
    @Column
    //@NotEmpty
    private String productId;

   // @NotEmpty
    @Column
    private String productName;
  //  @NotNull
    @Column
    private String productReference;
    @Column
    private String productMarque;

 //   @Column(name = "productIcon", length = 10000000)
   // private byte[] productIcon;
 //@Column
 //@NotEmpty
// private long productIcon;

   // @NotNull
   @Column
    private String  productTyp;
    @Column
  //  @NotEmpty
    private long providerId;

    @Column
    @Min(1)
    private int count;


    public ProductInOrder(Product productInfo, Integer quantity) {
        this.productId = " "+productInfo.getId();
        this.providerId = productInfo.getProvider().getId();
        this.productName = productInfo.getName();
        this.productMarque = productInfo.getMarque();
        this.productReference = productInfo.getReference();
      //  this.productIcon = productInfo.getPr_images().get(0).getPicByte();
     this.productIcon = productInfo.getPr_images().get(0);
        this.productTyp = productInfo.getSpeciality().getName();
        this.count = quantity;
    }

    public ProductInOrder() {
    }

    public Long getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public ImageModel getProductIcon() {
        return productIcon;
    }


    public void setProductIcon(ImageModel productIcon) {
        this.productIcon = productIcon;
    }

    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", productName='" + productName + '\'' +
                ", productReference='" + productReference + '\'' +
                ", productIcon='" + productIcon.getId() + '\'' +
                ", categoryType=" + productTyp +
                ", count=" + count +
                '}';
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public String getProductMarque() {
        return productMarque;
    }

    public void setProductMarque(String productMarque) {
        this.productMarque = productMarque;
    }

    public String getProductTyp() {
        return productTyp;
    }

    public void setProductTyp(String productTyp) {
        this.productTyp = productTyp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
     ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productReference, that.productReference) &&
                Objects.equals(productMarque, that.productMarque) &&
            //    Objects.equals(productIcon, that.productIcon) &&
                Objects.equals(productTyp, that.productTyp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, productId, productName, productMarque,productReference, productIcon.getId(), productTyp);
    }
}

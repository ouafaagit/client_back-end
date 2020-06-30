package client_back1.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * OrderMain contains User info and products in the order
 */
@Getter
@Setter
@Entity
/*@Data
@NoArgsConstructor
@DynamicUpdate*/
public class OrderMain implements Serializable {
    private static final long serialVersionUID = -3819883511505235030L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "orderMain")
    private Set<ProductInOrder> products = new HashSet<>();
    @Column
    @NotEmpty
    private String buyerEmail;
    @Column
    @NotEmpty
    private String buyerName;
    @Column
    @NotEmpty
    private String buyerPhone;
    @Column
    @NotEmpty
    private String buyerAddress;
    private Long userId;
    /**
     * default 0: new order.
     */

    @ColumnDefault("0")
    private Integer orderStatus;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public OrderMain(Doctor buyer) {
        this.buyerEmail = buyer.getEmail();
        this.buyerName = buyer.getFirstname()+" "+buyer.getLastname();
        this.buyerPhone = buyer.getPhone();
        this.buyerAddress = buyer.getAddress();
        this.orderStatus = 0;

    }
    public OrderMain(OrderMain buyer) {
        this.buyerEmail = buyer.getBuyerEmail();
        this.buyerName = buyer.getBuyerName();
        this.buyerPhone = buyer.getBuyerPhone();
        this.buyerAddress = buyer.getBuyerAddress();
        this.orderStatus = 0;
        this.userId = buyer.getUserId();

    }

    public OrderMain(@NotEmpty String buyerEmail, @NotEmpty String buyerName, @NotEmpty String buyerPhone, @NotEmpty String buyerAddress, Long userId) {
        this.buyerEmail = buyerEmail;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.userId = userId;
        this.orderStatus = 0;
    }

    public OrderMain() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Set<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInOrder> products) {
        this.products = products;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

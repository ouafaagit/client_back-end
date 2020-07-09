package client_back1.demo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@DiscriminatorValue("D")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table( name = "doctor")
//@JsonIgnoreProperties("speciality")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Doctor extends User {
/*	@Id
	@Column
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 @OneToOne
	 User user;*/
	/*@Column
	private String email;
	@Column
	@Size(min = 6, message = "Length must be more than 6")
	private String password;*/
	@Column
	private String lastname;
	@Column
	private String firstname;
	@Column
	private String phone;
	@Column
	private String address;
	/*@Column
	private boolean active;

	@NotEmpty
	@Column
	private String role = "ROLE_DOCTOR";*/

	@ManyToOne
	//@JsonManagedReference(value="special-doctors")

	private Speciality speciality;

/*	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Quotation>  cartt=new ArrayList<Quotation>();*/
/*@OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonIgnore
private Quotation cartt;*/
@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonIgnore  // fix bi-direction toString() recursion problem
private Cart cart;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "wishlist_product",
			joinColumns = @JoinColumn(name = "wishlist_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))

	Set<Product> wishlist=new HashSet<Product>();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "order_product",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	List<Product> orderproduct=new ArrayList<>();

	public Doctor() {
		super();
	}

	public Doctor(String g, String p, String lastnam, String firstnam, String phon, String speciality) {

		Speciality specialit =new Speciality(speciality);
		this.speciality = specialit;
	}

/*
	public Doctor(Long id) {
		this.id=id;
	}
*/


	@Override
	public String toString() {
		return super.toString()+"Doctor{" +
				"speciality=" + speciality +
				'}';
	}
/*	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}*/

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Product> getOrderproduct() {
		return orderproduct;
	}

	public void setOrderproduct(List<Product> orderproduct) {
		this.orderproduct = orderproduct;
	}

	public List<Product> addOrderproduct(Product p) {
		orderproduct.add(p);
		return orderproduct;
	}
	public List<Product> deleteOrderproduct(Product p) {
		orderproduct.remove(p);
		return orderproduct;
	}


	public Set<Product> getWishlist() {
		return wishlist;
	}

	public void setWishlist(Set<Product> wishlist) {
		this.wishlist = wishlist;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
/*
	public List<Quotation> getCartt() {
		return cartt;
	}

	public void setCartt(List<Quotation> cartt) {
		this.cartt = cartt;
	}

	public Quotation getCart() {
		if(this.cartt.size()>=1){
			return this.cartt.get(this.cartt.size()-1);
		}
		return null;
	}

	public void setCart(Quotation cart) {
		this.cartt.add(cart);
	}*/

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
/*	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}*/
/*public Long getId() {
	return id;
}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

}

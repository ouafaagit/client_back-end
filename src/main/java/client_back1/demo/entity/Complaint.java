package client_back1.demo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@JsonIgnoreProperties({ "complaint_prod"})
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String objet;
	@Column
	private String name;
	@Column
	private String message;
	@Column
	private String email;
	@Column
	private boolean status=false;
	//@JsonProperty( "complaint_prod" )
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getObjet() {
		return this.objet;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
//	public int hashCode() {
//		int lHashCode = 0;
//		if ( this.objet != null ) {
//			lHashCode += this.objet.hashCode();
//		}
//		if ( this.name != null ) {
//			lHashCode += this.name.hashCode();
//		}
//		if ( this.message != null ) {
//			lHashCode += this.message.hashCode();
//		}
//		if ( this.email != null ) {
//			lHashCode += this.email.hashCode();
//		}
//		if ( this.idProduct != null ) {
//			lHashCode += this.idProduct.hashCode();
//		}
//		if ( lHashCode == 0 ) {
//			lHashCode = super.hashCode();
//		}
//		return lHashCode;
//	}
//
//	public boolean equals(Object object) {
//		if (this == object) {
//			return true;
//		} else if (object instanceof Complaint) {
//			Complaint lComplaintObject = (Complaint) object;
//			boolean lEquals = true;
//			lEquals &= this.id == lComplaintObject.id;
//			lEquals &= ((this.objet == lComplaintObject.objet)
//				|| (this.objet != null && this.objet.equals(lComplaintObject.objet)));
//			lEquals &= ((this.name == lComplaintObject.name)
//				|| (this.name != null && this.name.equals(lComplaintObject.name)));
//			lEquals &= ((this.message == lComplaintObject.message)
//				|| (this.message != null && this.message.equals(lComplaintObject.message)));
//			lEquals &= ((this.email == lComplaintObject.email)
//				|| (this.email != null && this.email.equals(lComplaintObject.email)));
//			lEquals &= ((this.idProduct == lComplaintObject.idProduct)
//				|| (this.idProduct != null && this.idProduct.equals(lComplaintObject.idProduct)));
//			return lEquals;
//		}
//		return false;
//	}


}

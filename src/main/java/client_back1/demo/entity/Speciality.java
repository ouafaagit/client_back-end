package client_back1.demo.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIgnoreProperties("products")
@JsonIdentityInfo(scope=Speciality.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    private String name;
    @Column
    private double priceSpeciality;
    @Column
    private boolean status=true;
    @OneToMany(mappedBy = "speciality")
   // @JoinColumn(name = "id_doctor", referencedColumnName = "id")
   // @JsonBackReference(value="special-doctors")

    List<Doctor> doctors = new ArrayList<Doctor>();
    @ManyToMany(mappedBy = "Specialities")

    List<Provider> providers = new ArrayList<Provider>();
    @OneToMany(mappedBy = "speciality")
  //  @JoinColumn(name = "id_product", referencedColumnName = "id")
    //JsonBackReference n est pas serialise
  //  @JsonBackReference(value="pro-speciality")
    //@JsonManagedReference(value="pro-speciality")
            List<Product> products = new ArrayList<Product>();

    public Speciality() {
    }

    public Speciality(String name) {
        this.name=name;
    }

    public Speciality(String name,double priceSpeciality) {
        this.name=name;
        this.priceSpeciality = priceSpeciality;
    }

    public Speciality(long id, double priceSpeciality, boolean status, List<Doctor> doctors, List<Provider> providers, List<Product> products) {
        this.id = id;
        this.priceSpeciality = priceSpeciality;
        this.status = status;
        this.doctors = doctors;
        this.providers = providers;
        this.products = products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPriceSpeciality(double priceSpeciality) {
        this.priceSpeciality = priceSpeciality;
    }

    public double getPriceSpeciality() {
        return this.priceSpeciality;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
    public void setDoctor(Doctor doctor) {
        this.doctors.add(doctor) ;
    }
    public void setProduct(Product product) {
        this.products.add(product) ;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }
    public void setProvider(Provider provider) {
        this.providers.add(provider) ;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}

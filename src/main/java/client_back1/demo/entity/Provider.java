package client_back1.demo.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@DiscriminatorValue("P")
@Table(name = "Provider")
@SecondaryTable(name = "Society",pkJoinColumns =@PrimaryKeyJoinColumn(name = "provid", referencedColumnName = "id") )
//@JsonIgnoreProperties("product")
@JsonIdentityInfo(scope=Provider.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Provider extends User{
/*    @Id
    @Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)    private Long id;*/

 /*   @Column
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
  /*  @Column
    private boolean active;*/

   /* @NotEmpty
    @Column
    private String role = "ROLE_PROVIDER";*/
   // @Column
   // private boolean admin=false;
    //status 2 creer 0 bloquer -1 supprimer 1 active
    @Column
    private int status=2;
   // @Column
  //  @OneToMany(mappedBy = "notification", cascade = CascadeType.PERSIST)
  //  private  List<String> notification = new ArrayList<String>();
    @Column
    private double pricesubscription;

    @OneToMany(mappedBy = "provider",
            cascade = CascadeType.PERSIST)
  //  @JsonBackReference(value="pro-provider")

   // @JsonManagedReference(value="pro-provider")
    List<Product> product = new ArrayList<Product>();
    //@OneToMany(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "idSpeciality", referencedColumnName = "id")
//@JsonFormat

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "provider_speciality",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))

    //@JsonProperty("Specialities")
   // @JsonBackReference
    List<Speciality> Specialities=new ArrayList<Speciality>();
//    AbstractAdmin abstractAdmin;
@Column(table ="Society" )
//@Column
private String name;
  // @Column(table ="Society" )
  //@Column
  //  private String numero_tel;
  //@Column
  @Column(table ="Society" )
    private String emailsociety;

    public Provider() {
    }

    public Provider(long l) {
        super(l);
    }

/*
    public Provider(Long id) {
       this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

/*    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    public double getPricesubscription() {
        return pricesubscription;
    }

    public void setPricesubscription(double pricesubscription) {
        this.pricesubscription = pricesubscription;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

 /*   public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }*/

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Product> getProduct() {
        return product;
    }

    public List<Speciality> getSpecialities() {
        return Specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        Specialities = specialities;
    }
    public void setSpecialitie(Speciality specialities) {
        Specialities.add(specialities) ;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailsociety() {
        return emailsociety;
    }

    public void setEmailsociety(String emailsociety) {
        this.emailsociety = emailsociety;
    }

    public boolean isBlocked() {
        if(this.status==1)
            return false;
        return true;
    }


}

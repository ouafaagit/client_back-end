package client_back1.demo.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
//@JsonIgnoreProperties({ "prod_image"})
//@JsonIgnoreProperties({"provider","speciality"})
@JsonIdentityInfo(scope=Product.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column
    private String catalogue;
  //  @Column(length = 30000)
  @Column(columnDefinition="TEXT")
    private String reference;
    @Column
    public  double nombreVue;
    @Column
    public  double nombrewish;
    @Column
    private String marque;
    @Column
    private boolean blocked=false;
    // private Provider provider;

   @OneToMany(
           mappedBy = "product",
           cascade = CascadeType.PERSIST
   )
   // @OneToMany(mappedBy="product")
  // @JsonBackReference(value="pro-image")

  // @JsonManagedReference(value="pro-image")
   private List<ImageModel> pr_images= new ArrayList<ImageModel>();
    //@ColumnDefault("0")
   // private String categorie;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_speciality")
 //   @JsonProperty( "prod_speciality" )
   // @JsonManagedReference(value="pro-speciality")
  //  @JsonBackReference(value="pro-speciality")
//@JsonIgnore
    private Speciality speciality;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_provider")
   // @JsonManagedReference(value="pro-provider")

   // @JsonBackReference(value="pro-provider")
    Provider provider;
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    List<Complaint> complaint= new ArrayList<Complaint>();
  /*  @ManyToMany(
            mappedBy = "products",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
List<Quotation> quotations = new ArrayList<Quotation>();*/


    @ManyToMany(
            mappedBy = "wishlist",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
List<Doctor> wishdoc = new ArrayList<Doctor>();

    @ManyToMany(
            mappedBy = "orderproduct",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
List<Doctor> orderdoc = new ArrayList<Doctor>();

    public Product() {
         nombreVue=0;
        nombrewish=0;
    }

    public double getNombreVue() {
        return nombreVue;
    }

    public void setNombreVue(double nombreVue) {
        this.nombreVue = nombreVue;
    }

    public double getNombrewish() {
        return nombrewish;
    }

    public void setNombrewish(double nombrewish) {
        this.nombrewish = nombrewish;
    }


    public List<Complaint> getComplaint() {
        return complaint;
    }
    public void setComplaint(Complaint complaint) {
        this.complaint.add(complaint);
    }
    public void setComplaints(ArrayList<Complaint> complaint) {
        this.complaint = complaint;
    }
/*    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }*/


    public List<Doctor> getWishdoc() {
        return wishdoc;
    }

    public void setWishdoc(List<Doctor> wishdoc) {
        this.wishdoc = wishdoc;
    }

    public List<Doctor> getOrderdoc() {
        return orderdoc;
    }

    public void setOrderdoc(List<Doctor> orderdoc) {
        this.orderdoc = orderdoc;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<ImageModel> getPr_images() {
        return pr_images;
    }

    public void setPr_images(List<ImageModel> pr_images) {
        this.pr_images = pr_images;
    }
    public void setPr_image(ImageModel pr_image) {
        this.pr_images.add(pr_image);
    }
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setId(long id) { this.id = id; }

    public long getId() { return this.id; }

    public void setName(String name) { this.name = name; }

    public String getName() { return this.name; }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return this.description; }

    public void setCatalogue(String catalogue) { this.catalogue = catalogue; }

    public String getCatalogue() { return this.catalogue; }

    public void setReference(String reference) { this.reference = reference; }

    public String getReference() { return this.reference; }

    public void setMarque(String marque) { this.marque = marque; }

    public String getMarque() {
        return this.marque;
    }


  /*  public List<ImageModel> getPr_images() {
        return pr_images;
    }

    public void setPr_images(List<ImageModel> pr_images) {
        this.pr_images = pr_images;
    }*/
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }



}

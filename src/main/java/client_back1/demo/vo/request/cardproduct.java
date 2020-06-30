package client_back1.demo.vo.request;

import client_back1.demo.entity.ImageModel;
import client_back1.demo.entity.Product;
import client_back1.demo.service.ImageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;

@Data
public class cardproduct {
    @NotBlank
    private String name;
    @NotBlank
    private String marque;
    @NotBlank
    private String reference;
    @NotBlank
    private String speciality;
    @NotBlank
    private long id;
    @NotBlank
    private byte[] picByte;

    public cardproduct(Product product)
    { System.out.println("cardproduct"+product.getId());
        name=product.getName();
        marque=product.getMarque();
        id=product.getId();
        reference=product.getReference();
        speciality=product.getSpeciality().getName();
        picByte=null;
   /*     if(product.getPr_images().get(0) !=null)
        {     ImageModel img= new ImageModel();
        img = imageService.findById(product.getPr_images().get(0).getId());
        picByte=img.getPicByte() ;
            System.out.println("img.getPicByte()"+img.getPicByte().length);
        }else{
            picByte=null;
        }*/
    }



    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}

package client_back1.demo.rest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import client_back1.demo.entity.ImageModel;
import client_back1.demo.entity.Product;
import client_back1.demo.repository.ImageRepository;
import client_back1.demo.service.ImageService;
import client_back1.demo.service.ProductService;
import client_back1.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/upload")

public class ImageUploadController {

    private final ProductService productService;
    private final ImageService imageService;
    @Autowired
    ImageRepository imageRepository;

    public ImageUploadController( ProductService productService, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @PostMapping("/image/{id}")
    public ImageModel uplaodImage(@PathVariable long id,@RequestParam("imageFile") MultipartFile image) throws IOException {
        return imageService.save(id,image);
    /*   System.out.println("Original Image Byte Size - " + image.getBytes().length);
        System.out.println(image.getOriginalFilename());
        ImageModel img = new ImageModel(image.getOriginalFilename(), image.getContentType(),  compressBytes(image.getBytes()));
        Product p = this.productService.findOne(id);
        p.setPr_image(img);
        img.setProduct(p);*/
        //p.getPr_images().add(img);

      // this.providerService.updateProduct(id,p);
      // return imageService.save(img);

       // return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping("/File/{id}")
    public long  uploadToLocalFileSystem(@PathVariable long id, @RequestParam("File") MultipartFile file) throws IOException {
        return  this.imageService.savefile(id,file);
    }
////////



    @PostMapping("/Filee/{id}")
    public Product uplaodFile(@PathVariable Long id, @RequestParam("File") MultipartFile file, HttpSession session) throws IOException {
        Path rootLocation = Paths.get(session.getServletContext().getRealPath("/resources/files"));
        System.out.println("rootLocation  ==  " + rootLocation);
        Product p = this.productService.findOne(id);
        String nameExtension[] = file.getContentType().split("/");

        //String profileImage = file.getOriginalFilename()+ "." + nameExtension[1];
        String profileImage = file.getOriginalFilename();
        System.out.println("ProfileImage  :: " + profileImage);
        Files.copy(file.getInputStream(),rootLocation.resolve(profileImage));
        p.setCatalogue(profileImage);
        System.out.println("ggg"+profileImage);

       // return  this.providerService.addProduct(p);
        return null;
       // System.out.println("Original file Byte Size - " + file.getBytes().length);
      //  System.out.println(file.getOriginalFilename());
       // ImageModel img = new ImageModel(image.getOriginalFilename(), image.getContentType(),  compressBytes(image.getBytes()));
        //Product p = this.providerService.getProduct(id);
      //  p.setFileByte( compressBytes(compressBytes(file.getBytes())));
      //  return  this.providerService.addProduct(p);
        // return ResponseEntity.status(HttpStatus.OK);
    }



    /*   @PostMapping("/uploadd")
    public ImageModel uplaodImage(@RequestParam("imageFile") MultipartFile image )throws IOException {
     //   Product p = this.providerService.getProduct(8);
     System.out.println("Original Image Byte Size - " + image.getBytes().length);
        System.out.println(image.getOriginalFilename());
        ImageModel img = new ImageModel(image.getOriginalFilename(), image.getContentType(),  compressBytes(image.getBytes()));
        return imageRepository.save(img);
        // img.setProduct(p);

        // return ResponseEntity.status(HttpStatus.OK);
    }*/
    @RequestMapping(
            value = ("/uupload"),
            headers = "content-type=multipart/form-data",
            method = RequestMethod.POST)
    public String uploadFile(@PathVariable Long id,@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());

        ImageModel imgg = new ImageModel(file.getOriginalFilename(), file.getContentType(),file.getBytes());
         imageRepository.save(imgg);
     //   System.out.println(dataType);
        return "File uploaded successfully.";
    }
    @PostMapping("/multi-upload")
    public List<ImageModel> multiUpload(@RequestParam("files") MultipartFile[] files) throws IOException {
        List<ImageModel> fileDownloadUrls = new ArrayList<>();
        for (MultipartFile file : Arrays.asList(files)) {
           // fileDownloadUrls.add(this.uplaodImage(file));
        }
return fileDownloadUrls;
    }
@GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
       // ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(),retrievedImage.getPicByte(),2);

    return imageService.findByName(imageName);
    }

    //delete from db in all
    @DeleteMapping("/delete_Image/{id}")
    public long delete(@PathVariable("id") long productId) {
        System.out.println("delete"+productId);
        imageService.delete(productId);
        return productId;
    }
}




/*import client_back1.demo.entity.ImageModel;
import client_back1.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "check")

public class TestController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    public ImageModel uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

        ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes() );


        final ImageModel savedImage = imageRepository.save(img);


        System.out.println("Image saved");


        return savedImage;


    }
}*/

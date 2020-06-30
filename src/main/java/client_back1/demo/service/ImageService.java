package client_back1.demo.service;

import client_back1.demo.entity.ImageModel;
import client_back1.demo.entity.Product;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.ImageRepository;
import client_back1.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {
    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    ProductRepository productRepository;
    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

   //add image
    public ImageModel save(long id, MultipartFile image) throws IOException {
        System.out.println("Original Image Byte Size - " + image.getBytes().length);
        System.out.println(image.getOriginalFilename());
        ImageModel img = new ImageModel(image.getOriginalFilename(), image.getContentType(),  compressBytes(image.getBytes()));
        Product p = this.productRepository.findById(id);
        p.setPr_image(img);
        //p.getPr_images().add(img);
        img.setProduct(p);
      return  this.imageRepository.save(img);
    }
    // id image
    public ImageModel getImage(long id){
        ImageModel image = this.imageRepository.findById(id).orElseThrow();

        return image;
    }
    //  imagename
  public ImageModel findByName(String name){
        ImageModel image = this.imageRepository.findByName(name).orElseThrow();
        ImageModel img = new ImageModel(image.getName(), image.getType(),
                decompressBytes(image.getPicByte()));
        return img;
    }
    //  imagename
    public ImageModel findById(long name){
        ImageModel image = this.imageRepository.findById(name).orElseThrow();
        ImageModel img = new ImageModel(image.getName(), image.getType(),
                decompressBytes(image.getPicByte()));
        img.setId(image.getId());
        return img;
    }
    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
/////////////file upload///////////////:

    public long  savefile( long id, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Product p = this.productRepository.findById(id);
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        p.setCatalogue("http://localhost:8080/images/"+file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Path path = Paths.get(absolutePath + "/src/main/resources/files/"+fileName);
        Files.write(path, bytes);
        this.productRepository.save(p);
        System.out.println("file upload path :"+path);
        return p.getId();
        }


    /////////////////////delete -image////

    public void delete(long productId) {
        ImageModel productInfo = imageRepository.getOne(productId);
        System.out.println("Supprimer image "+productId);
        Product p=productInfo.getProduct();
        p.getPr_images().remove(productInfo);
        imageRepository.delete(productInfo);

    }

      /*  Path path = Paths.get("/resources/files/"+fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resources/files/")
                .path(fileName)
                .toUriString();
        Product p = this.providerService.getProduct(id);
        //return ResponseEntity.ok(fileDownloadUri);
        p.setCatalogue(fileDownloadUri);
        System.out.println("ggg"+fileDownloadUri);*/

    }
    //liste images by id product
  /*  public Page<ImageModel> getImages(Long id){

        return this.imageRepository.findByProductId(id);
    }
*/
   /* public Image updateImage(Image image){
        return this.imageRepository.save(image);
    }*/

//    public String getImageByClientId(Long id){
//        Image image = this.imageRepository.findFirstByClientId(id);
//        if(!image.equals(null))
//            return image.getPath();
//        else
//            return "L'image n'existe pas";
//    }
//    public void deleteImages(Long id){
//        this.imageRepository.deleteAllByClientId(id);
//    }


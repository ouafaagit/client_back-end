package client_back1.demo.service;

import client_back1.demo.entity.Product;
import client_back1.demo.vo.request.cardproduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
///////////////////:user//////////////////////
    //products by speciality name
    Product getproductU(long productId);

    Product getproductUp(long productId);

    /// One product by speciality name findAllByBlockedIsFalseAndSpecialityName
    Page<Product> getproducts_sp(String name, Pageable pageable);

    Product findOne(long productId);

    // All selling products
    Page<Product> findUpAll(Pageable pageable);
    //////////////////////////admin//////////////////

    void delete(long productId);

    ///Get product detail
    Product getproductAd(long productId);

    List<Product> findAll();

    //bolck product
    void offSale(long productId);
    //deblock
    void onSale(long productId);
/////////////////////////////:provider///////////////////////////
    //for  provider list
  //  Page<Product> findAllProductprovider(long idprovider, Pageable pageable);

    //////////////////////////////provider////////////////////
        //for  provider list
    //List<Product> findAllProductprovider(long idprovider);

    //////////////////////////////provider////////////////////
        //for  provider list
    List<Product> findAllProductprovider(long idprovider);

    long update(Product productInfo);
    long save(Product productInfo);


    List<cardproduct> Diagnostinew();

    List<cardproduct> Diagnostiautre();

    List<cardproduct> Diagnostivendu();

    List<cardproduct> logicienew();

    List<cardproduct> logicievendu();

    List<cardproduct> logicieautre();

    List<cardproduct> tendancnew();

    List<cardproduct> findAllproducts();

    Product quickview(long productId);

    List<cardproduct> relatedprod(long spcialityid);

    List<cardproduct> Allnewprod();

    List<cardproduct> findAllbysp(long id);
}

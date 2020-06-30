package client_back1.demo.service.impl;

import client_back1.demo.entity.*;
import client_back1.demo.enums.ResultEnum;
import client_back1.demo.exception.MyException;
import client_back1.demo.repository.ComplaintRepository;
import client_back1.demo.repository.ProductRepository;
import client_back1.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service

public class ComplaintServiceImpl implements ComplaintService {
@Autowired
ComplaintRepository complaintRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Complaint findOne(long id) {
        return complaintRepository.getOne(id);
    }


    @Override
    public Complaint save(long id, Complaint complaint) {
     /*   if(complaint==null){System.out.println("null complaint");
        return new Complaint();}*/
        Product product =productRepository.findByIdAndBlockedIsFalse(id);
        product.setComplaint(complaint);
        complaint.setProduct(product);
        System.out.println("----"+complaint.getName()+complaint.getObjet());
        return complaint;
    }

    @Override
    public List<Complaint> getALLcomplaintpr(long id) {
        List<Complaint>  l= complaintRepository.findByProductProviderIdAndStatusTrueOrderByIdDesc(id);
        List<Complaint>  ll=new ArrayList<Complaint>();
        for ( int j=0;j<l.size();j++)
        {   Complaint ss=new Complaint();
            ss.setName(l.get(j).getName());
            ss.setObjet(l.get(j).getObjet());
            ss.setId(l.get(j).getId());
            System.out.println("complaint"+ss.getName());
            ss.setEmail(l.get(j).getEmail());
            Product product=new Product();
            product.setName(l.get(j).getProduct().getName());
            product.setId(l.get(j).getProduct().getId());
            product.setSpeciality(l.get(j).getProduct().getSpeciality());
            ss.setProduct(product);
            ss.setEmail(l.get(j).getEmail());
            ss.setMessage(l.get(j).getMessage());
            ll.add(ss);
        }
        return ll;
    }

    @Override
    public List<Complaint> getALLcomplaint() {
        List<Complaint>  l=complaintRepository.findAllByStatusIsTrue();
        List<Complaint>  ll=new ArrayList<Complaint>();
        for ( int j=0;j<l.size();j++)
        {   Complaint ss=new Complaint();
            ss.setName(l.get(j).getName());
            ss.setObjet(l.get(j).getObjet());
            ss.setId(l.get(j).getId());
            System.out.println("complaint"+ss.getName());
            ss.setEmail(l.get(j).getEmail());
            Product product=new Product();
            product.setName(l.get(j).getProduct().getName());
            product.setId(l.get(j).getProduct().getId());
            product.setSpeciality(l.get(j).getProduct().getSpeciality());
            Provider provider=new Provider();
            provider.setLastname(l.get(j).getProduct().getProvider().getLastname());
            provider.setId(l.get(j).getProduct().getProvider().getId());
            product.setProvider(provider);
            ss.setProduct(product);
            ss.setEmail(l.get(j).getEmail());
            ss.setMessage(l.get(j).getMessage());
            ll.add(ss);
            // System.out.println("$$$$$"+ll.get(j).getName());

        }
        return ll;
    }
    ///delete on db
    @Override
    public void delete(long productId) {
        System.out.println("supprimer :"+productId);
        complaintRepository.deleteById(productId);
    }
 /*   @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }*/

}

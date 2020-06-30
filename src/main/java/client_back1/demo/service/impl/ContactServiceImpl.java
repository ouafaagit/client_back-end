package client_back1.demo.service.impl;

import client_back1.demo.entity.Contact;
import client_back1.demo.entity.Product;
import client_back1.demo.entity.Provider;
import client_back1.demo.repository.ContactRepository;
import client_back1.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
@Autowired
ContactRepository contactRepository ;


    @Override
    public Contact findOne(long id) {
        return contactRepository.getOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        System.out.println("----"+contact.getName()+contact.getObjet());
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getALLcontact() {
        List<Contact>  l=contactRepository.findAll();
        List<Contact>  ll=new ArrayList<Contact>();
        for ( int j=0;j<l.size();j++)
        {   Contact ss=new Contact();
            ss.setName(l.get(j).getName());
            ss.setObjet(l.get(j).getObjet());
            ss.setId(l.get(j).getId());
            ss.setPhone(l.get(j).getPhone());
            System.out.println("contact"+ss.getName());
            ss.setEmail(l.get(j).getEmail());
            ss.setMessage(l.get(j).getMessage());
            ll.add(ss);

        }
        return ll;
    }
    ///delete on db
    @Override
    public void delete(long productId) {
        System.out.println("supprimer :"+productId);
        contactRepository.deleteById(productId);
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

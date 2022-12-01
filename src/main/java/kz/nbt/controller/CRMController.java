package kz.nbt.controller;


import kz.nbt.entity.CRM;
import kz.nbt.entity.Messages;
import kz.nbt.repo.CRMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRMController {

    @Autowired
    CRMRepo crmRepo;

    @GetMapping(path="/getCustomerInfo",  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CRM customerInfo(@RequestParam(required=false) String clientid) throws Exception {
        return  crmRepo.findAllByClientid(clientid);
    }

    @PostMapping(path = "/addCustomer", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCustomer(@RequestBody CRM customer){

        CRM customerObj = new CRM();
        customerObj.setName(customer.getName());
        customerObj.setAddress(customer.getAddress());
        customerObj.setClientid(customer.getClientid());
        customerObj.setSurname(customer.getSurname());
        customerObj.setEmail(customer.getEmail());
        customerObj.setBalance(customer.getBalance());
        crmRepo.save(customerObj);
        return new ResponseEntity(customerObj, HttpStatus.CREATED);
    }



}

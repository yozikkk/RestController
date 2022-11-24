package kz.nbt.controller;


import kz.nbt.entity.History;
import kz.nbt.repo.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public class HistoryController {


    @Autowired
    HistoryRepo historyRepo;


    @Transactional
    @PostMapping(path="/collect",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<History> collect(@RequestBody History object){


        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }





}

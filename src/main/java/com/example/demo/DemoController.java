package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author chengj17
 * @since 2021/2/4
 */
@RestController
public class DemoController {

    /**
     *
     * @param person
     */
    @PostMapping(value = "/json2properties",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/properties+person")
    public Person json2properties(@RequestBody Person person){

        return person;
    }

    /**
     *
     * @param person
     */
    @PostMapping(value = "/properties2json",consumes = "application/properties+person",produces = MediaType.APPLICATION_JSON_VALUE)
    public Person properties2json(@RequestBody Person person){

        return person;
    }
}

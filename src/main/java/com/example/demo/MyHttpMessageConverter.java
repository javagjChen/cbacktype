package com.example.demo;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author chengj17
 * @since 2021/2/4
 */
public class MyHttpMessageConverter extends AbstractHttpMessageConverter<Person> {

    public MyHttpMessageConverter(){
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }
    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream inputStream = inputMessage.getBody();

        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));

        Person person = new Person();
        person.setAge(Integer.valueOf(properties.getProperty("person.age")));
        person.setName(properties.getProperty("person.name"));
        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Properties properties = new Properties();
        properties.setProperty("person.age",person.getAge().toString());
        properties.setProperty("person.name",person.getName());
        properties.store(new OutputStreamWriter(outputMessage.getBody(),getDefaultCharset()),"");
    }
}

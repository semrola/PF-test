package org.primefaces.test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Data;

@Named
@ViewScoped
public class TestView implements Serializable {
    
    private String string;
    private Integer integer;
    private BigDecimal decimal;
    private LocalDateTime localDateTime;

    private Map<String, Object> map;

    @PostConstruct  
    public void init() {
        string = "Welcome to PrimeFaces!!!";
        map = new HashMap<>();
    }

    public void save() {
        System.out.println(map);
    }

    public Date newDate() {
        return new Date();
    }

    public Map<String, Object> getMap() {
        return map;
    }
}

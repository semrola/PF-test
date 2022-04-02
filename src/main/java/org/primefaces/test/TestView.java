package org.primefaces.test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {
    
    private String string;
    private Integer integer;
    private BigDecimal decimal;
    private LocalDateTime localDateTime;
    private LazyDataModel<String> model;

    private List<String> list;

    @PostConstruct  
    public void init() {
        string = "Welcome to PrimeFaces!!!";

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        model = new LazyDataModel<String>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return list.size();
            }

            @Override
            public List<String> load(int first, int page, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                testMsgs();
                PrimeFaces.current().ajax().update("msgs");
                System.out.println("here");
                return list.subList(first, first + page);
            }
        };
    }

    public void testMsgs() {
        FacesMessage msg = new FacesMessage("Test message");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LazyDataModel<String> getModel() {
        return model;
    }


}

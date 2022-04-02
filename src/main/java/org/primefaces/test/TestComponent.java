package org.primefaces.test;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@FacesComponent("testComponent")
public class TestComponent extends UIInput implements NamingContainer {

    private UIInput from;
    private UIInput to;
    private static final String DELIMITER = "-->";

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        DateRange range = (DateRange) getValue();
        System.out.println(range);
        if (range != null) {
            from.setValue(range.getFrom());
            to.setValue(range.getTo());
        }
        super.encodeBegin(context);
    }

    @Override
    public Object getSubmittedValue() {
        if (from == null || to == null) return null;

        String submittedFrom = (String) from.getSubmittedValue();
        String submittedTo = (String) to.getSubmittedValue();

        if (submittedFrom == null) {
            submittedFrom = "";
        }
        if (submittedTo == null) {
            submittedTo = "";
        }

        return submittedFrom + DELIMITER + submittedTo;
//		return new DateRange((Date) dateFrom.getValue(), (Date) dateTo.getValue());
    }

    @Override
    protected Object getConvertedValue(FacesContext context, Object newSubmittedValue) throws ConverterException {
        String submitted = (String) newSubmittedValue;
        if (submitted == null || submitted.isEmpty()) {
            return null;
        }

        String[] split = submitted.split(DELIMITER);

        String fromStr = split.length > 0 ? split[0] : "";
        String toStr = split.length > 1 ? split[1] : "";

        

        Object fromObj = null;
        Object toObj = null;
        fromObj = from.getConverter().getAsObject(FacesContext.getCurrentInstance(), from, fromStr);
        toObj = to.getConverter().getAsObject(FacesContext.getCurrentInstance(), to, toStr);

        return new DateRange((Date) fromObj, (Date) toObj);
    }

    public UIInput getFrom() {
        return from;
    }

    public void setFrom(UIInput from) {
        this.from = from;
    }

    public UIInput getTo() {
        return to;
    }

    public void setTo(UIInput to) {
        this.to = to;
    }

    public static void main(String[] args) {
        String s = "-->";
        String a = "" + s + "s";
        List<String> collect = Arrays.stream(a.split(s)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect.size());
    }
}

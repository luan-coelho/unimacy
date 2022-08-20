package br.unitins.unimacy.converter.jsf;

import org.jboss.weld.bean.AbstractBean;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "primeFacesPickListConverter")
public class PrimeFacesPickListConverter implements Converter<Object> {

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object ret = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            DualListModel<?> dl = (DualListModel<?>) dualList;
            for (Object o : dl.getSource()) {
                String id = ((AbstractBean<?, ?>) o).getId();
                if (arg2.equals(id)) {
                    ret = o;
                    break;
                }
            }

            if (ret == null) {
                for (Object o : dl.getTarget()) {
                    String id = ((AbstractBean<?, ?>) o).getId();
                    if (arg2.equals(id)) {
                        ret = o;
                        break;
                    }
                }

            }
        }
        return ret;
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String str = "";
        if (arg2 instanceof AbstractBean) str = ((AbstractBean<?, ?>) arg2).getId();
        return str;
    }
}
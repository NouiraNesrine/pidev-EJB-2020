package workspace;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import entities.User;

public class UserConverter implements Converter {
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        int i = ((User) object).getIdUser();
        String o = i+"";
		return o;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        //List<Skills> skills = (List<Skills>) context.getApplication().evaluateExpressionGet(context.getELContext(), "#{data.languages}", List.class);

        ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{missionBean.allSkills}", List.class);
        List<User> user = (List<User>) ve.getValue(context.getELContext());
        
        for (User u : user) {
        	String id =""+u.getIdUser();
            if (id.equals(submittedValue)) {
                return u;
            }
        }

        return null;
    }

}

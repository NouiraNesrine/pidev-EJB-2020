package workspace;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import entities.Skills;
@FacesConverter("skillConverter")
public class SkillConverter implements Converter{

	  @Override
	    public String getAsString(FacesContext context, UIComponent component, Object object) {
	        return ((Skills) object).getNomCompetence();
	    }

	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
	        //List<Skills> skills = (List<Skills>) context.getApplication().evaluateExpressionGet(context.getELContext(), "#{data.languages}", List.class);

	        ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{missionBean.allSkills}", List.class);
	        List<Skills> skills = (List<Skills>) ve.getValue(context.getELContext());
	        
	        for (Skills skill : skills) {
	            if (skill.getNomCompetence().equals(submittedValue)) {
	                return skill;
	            }
	        }

	        return null;
	    }
}

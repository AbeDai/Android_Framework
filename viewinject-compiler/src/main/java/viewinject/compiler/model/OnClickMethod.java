package viewinject.compiler.model;

import com.example.OnClick;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;

public class OnClickMethod {

    private ExecutableElement methodElement;
    private Name mMethodName;
    private boolean isExistParameters;
    public int[] ids;

    public OnClickMethod(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException(
                    String.format("Only methods can be annotated with @%s", OnClick.class.getSimpleName()));
        }
        this.methodElement = (ExecutableElement) element;
        this.ids = methodElement.getAnnotation(OnClick.class).value();

        if (ids == null) {
            throw new IllegalArgumentException(String.format("Must set valid ids for @%s", OnClick.class.getSimpleName()));
        } else {
            for (int id : ids) {
                if (id < 0) {
                    throw new IllegalArgumentException(String.format("Must set valid id for @%s", OnClick.class.getSimpleName()));
                }
            }
        }
        this.mMethodName = methodElement.getSimpleName();
        List<? extends VariableElement> parameters = methodElement.getParameters();
        if (parameters.size() > 1) {
            throw new IllegalArgumentException(
                    String.format("The method annotated with @%s must have one parameter or no parameters", OnClick.class.getSimpleName()));
        } else {
            isExistParameters = parameters.size() > 0;
        }
    }

    public Name getMethodName() {
        return mMethodName;
    }

    public boolean isExistParameters(){
        return isExistParameters;
    }
}

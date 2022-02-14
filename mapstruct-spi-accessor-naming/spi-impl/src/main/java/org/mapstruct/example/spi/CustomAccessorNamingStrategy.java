/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.example.spi;

import java.beans.Introspector;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import org.mapstruct.ap.spi.AccessorNamingStrategy;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.mapstruct.ap.spi.MethodType;
import org.mapstruct.ap.spi.util.IntrospectorUtils;

/**
 * A custom {@link AccessorNamingStrategy} recognizing getters in the form of {@code property()} and setters in the
 * form of {@code withProperty(value)}.
 *
 * @author Sjaak Derksen
 */
public class CustomAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

    @Override
    public boolean isGetterMethod(ExecutableElement method) {
        if (!method.getParameters().isEmpty()) {
            return false;
        }
        String methodName = method.getSimpleName().toString();
        System.out.println("In Getter for " + methodName + " with return type " + method.getReturnType().getKind());
        return !methodName.startsWith("with") && method.getReturnType().getKind() != TypeKind.VOID;
    }

    @Override
    public boolean isSetterMethod(ExecutableElement method) {
        String methodName = method.getSimpleName().toString();
        System.out.println("In isSetterMethod for " + methodName);
        boolean ans = super.isSetterMethod(method) || methodName.startsWith("with") && methodName.length() > 4;
        System.out.println("Exiting isSetterMethod with ans " + ans);
        return ans;
    }

    @Override
    public String getPropertyName(ExecutableElement getterOrSetterMethod) {
        String methodName = getterOrSetterMethod.getSimpleName().toString();
        System.out.println("In getPropertyName for " + methodName);
        String propertyName;
        /**
         * Check if it is a fluentSetter
         */
        if (super.isFluentSetter(getterOrSetterMethod))
            propertyName = methodName.startsWith("set") && methodName.length() > 3 && Character.isUpperCase(methodName.charAt(3)) ? methodName.substring(3) : methodName;
        else if (methodName.startsWith("with"))
            propertyName = methodName.substring(4);
        else if (methodName.startsWith("set"))
            propertyName = methodName.substring(3);
        else if (methodName.startsWith("get"))
            propertyName = methodName.substring(3);
        else if (methodName.startsWith("is"))
            propertyName = methodName.substring(2);
        else
            propertyName = methodName;
        System.out.println("Exiting getPropertyName with propName " + propertyName);
        return Introspector.decapitalize(propertyName);
    }

    @Override
    public boolean isFluentSetter(ExecutableElement method) {
        System.out.println("In isFluentSetter");
        boolean ans = super.isFluentSetter(method) && !method.getSimpleName().toString().equals("from");
        System.out.println("Exiting isFluentSetter with ans " + ans);
        return ans;
    }

}

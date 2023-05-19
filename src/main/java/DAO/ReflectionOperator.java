package DAO;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionOperator {
    private  ArrayList<String>listField=new ArrayList<String>();
    private  ArrayList<Object>listValue=new ArrayList<Object>();
    public ReflectionOperator() {
        super();
    }

    public void retrieveProperties(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if(!field.getName().equals("id")) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                    listValue.add(value);
                    listField.add(field.getName());
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<String> getListField() {
        return listField;
    }
    public void setListField(ArrayList<String> listField) {
        this.listField = listField;
    }
    public ArrayList<Object> getListValue() {
        return listValue;
    }
    public void setListValue(ArrayList<Object> listValue) {
        this.listValue = listValue;
    }


}

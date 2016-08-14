/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;


/**
 *
 * @author Tuhama
 * not used for now
 */
public class JsonExcludeStrategy  implements ExclusionStrategy {
    private final Class classToExclude= entity.Employee.class;
 
    public JsonExcludeStrategy() {
        
    }
 
    // This method is called for all fields. if the method returns false the
    // field is excluded from serialization
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        
        if (f.getName().equalsIgnoreCase("employeeId"))
            return false;
 
        return true;
    }
 
    // This method is called for all classes. If the method returns false the
    // class is excluded.
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz.equals(classToExclude))
            return true;
        return false;
    }
}

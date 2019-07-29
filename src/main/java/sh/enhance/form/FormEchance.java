package sh.enhance.form;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.lang.reflect.Field;

public interface FormEchance {

    default void parseJSON(JSONObject jsonObject) throws IllegalAccessException {
        if (jsonObject == null){
            return;
        }
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            JSONField jsonField = field.getAnnotation(JSONField.class);
            String filedName = null;
            if (jsonField != null){
                filedName = jsonField.name();
            }else {
                filedName = field.getName();
            }
            field.set(this,jsonObject.get(filedName));
        }
    }
}

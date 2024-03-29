package sh.base.form;

import sh.base.entity.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseForm<T extends BaseEntity> {

    protected T entity;

    protected String dataFormat = "yyyy-MM-dd HH:mm:ss";
    /*
    * Form对象转entity对象
    * */
    public T FormToEntity(T entity) throws Exception{
        return entity;
    }

    public void EntityToForm(T entity)throws Exception{
        autoEntityToForm(entity);
    }

    private void autoEntityToForm(T entity)throws Exception{
        Class eclazz = entity.getClass();
        Class fclazz = this.getClass();
        Map<String,Field> efieldmap = new HashMap<>();
        Field[] fields = null;
        for (; eclazz != Object.class; eclazz = eclazz.getSuperclass()){
            fields = eclazz.getDeclaredFields();
            for (Field field : fields){
                if (!efieldmap.containsKey(field.getName())){
                    efieldmap.put(field.getName(),field);
                }
            }
        }
        for (String key : efieldmap.keySet()){
            try {
                Field field = efieldmap.get(key);
                field.setAccessible(true);
                Object object = field.get(entity);
                if (object == null){
                    continue;
                }
                String name = field.getName();
                char[] names = name.toCharArray();//因为要通过目标类的setter方法赋值，所以要通过首字母大写拼成setter方法
                if (!Character.isUpperCase(names[0])){
                    names[0]-=32;
                }
                name = String.valueOf(names);
                Class type = field.getType();
                Method method = null;
                if (type.getName().equals("java.utils.Date")){
                    method = fclazz.getMethod("set" + name,String.class);
                    SimpleDateFormat df = new SimpleDateFormat(dataFormat);//设置日期格式
                    object = df.format(object);
                }else {
                    method = fclazz.getMethod("set" + name,type);
                }
                if (method != null){
                    method.invoke(this,object);
                }
            }catch (Exception e){

            }

        }
    }

}

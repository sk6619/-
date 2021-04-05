package com.shaokui.reflect.exercise.ioc;

import com.shaokui.reflect.exercise.annotation.Autowire;
import com.shaokui.reflect.exercise.annotation.Repository;
import com.shaokui.reflect.exercise.annotation.Value;
import com.shaokui.reflect.exercise.tool.MyTool;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>文档描述：IOC容器</p>
 *
 * @Author MyAnnotationAppConfigContext
 * @Date 2021/4/4 0004 下午 5:01
 * @Version 1.0
 */
public class MyAnnotationAppConfigContext {

    //通过类型获取对象
    private final Map<BeanDefination, Object> beanMap = new HashMap<>();
    //存放BeanDefination
    private Set<BeanDefination> beanDefinitions = new HashSet<>();

    public MyAnnotationAppConfigContext(String packageName) {
        initContext(packageName);
    }

    //初始化容器
    private void initContext(String packageName) {
        beanDefinitions = findBeanDefination(packageName);
        createBean();
    }


    //找到BeanDefination
    private Set<BeanDefination> findBeanDefination(String packgeName) {
        //1.找到该包下所有的类
        List<String> classes = MyTool.getClasses(packgeName);
        classes.forEach(i -> {
            try {
                //2.遍历这些类，找到添加了注解的类
                Class<?> beanClass = Class.forName(i);
                Repository repository = beanClass.getAnnotation(Repository.class);
                //如果注解value不为空
                if (repository != null) {
                    String value = repository.value();
                    if (!value.equals("")) {
                        beanDefinitions.add(new BeanDefination(beanClass, value));
                    } else {
                        //获取类名并且转换为小谢
                        String beanName = i.replace(packgeName + ".", "").toLowerCase();
                        //3.将这些类封装成BeanDefination,装入集合
                        beanDefinitions.add(new BeanDefination(beanClass, beanName));
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return beanDefinitions;
    }

    //通过名字获取对象
    public Object getBean(String beanName) {
        Object object;
        //从缓存里面找
        Set<BeanDefination> beanDefination = beanMap.keySet();
        for (BeanDefination bean : beanDefination) {
            String beanName1 = bean.getBeanName();
            if (beanName.equals(beanName1)) {
                object = beanMap.get(bean);
                return object;
            }
        }
        return null;
    }

    //通过类型获取对象
    public Object getBean(Class<?> beanClass) {

        //从缓存里面找
        Set<BeanDefination> beanDefination = beanMap.keySet();
        for (BeanDefination bean : beanDefination) {
            Class<?> beabClass = bean.getBeabClass();
            if (beanClass == beabClass) {
                return beanMap.get(bean);
            }
        }
        return null;
    }

    //创建对象放入缓存
    public void createBean() {
        beanDefinitions.forEach(i -> {
            Class<?> beanClass = i.getBeabClass();
            Field[] fields = beanClass.getDeclaredFields();
            try {
                Object object = beanClass.newInstance();
                beanMap.put(i, object);
                for (Field field : fields) {
                    Value valueAnnotation = field.getAnnotation(Value.class);
                    if (valueAnnotation != null) {
                        //获取注解的值，通过set方法设值
                        String value = valueAnnotation.value();
                        //获取字段名
                        String fieldName = field.getName();
                        Class<?> fieldType = field.getType();
                        //给属性设值，反射执行invoke方法
                        setFieldValue(beanClass,object,value, fieldName, fieldType);
                    }
                    //加了自动注入的注解
                    Autowire autowire = field.getDeclaredAnnotation(Autowire.class);
                    if(autowire != null) {
                        String fieldName = field.getName();
                        Class<?> fieldType = field.getType();
                        Object bean = getBean(fieldName);
                        Method method = beanClass.getDeclaredMethod("set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1), fieldType);
                        method.invoke(object, bean);
                    }
                }
                beanMap.put(i, object);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }


    //注解字段类型转换
    public Object turnField(String fieldType,String value) {
        Object val = null;
        switch (fieldType) {
            case "java.lang.String":
                val = value;
                break;
            case "java.lang.Integer":
                val = Integer.valueOf(value);
                break;
        }
        return val;
    }

    public void setFieldValue(Class<?> beanClass,Object object,String annotationValue,String fieldName,Class<?> fieldType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取字段对应set方法
        Method method = beanClass.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fieldType);
        //字段注解的值类型转换
        Object val = turnField(fieldType.getName(),annotationValue);
        method.invoke(object, val);
    }

    public List<Object> getAllBeans(){
        ArrayList<Object> objects = new ArrayList<>();
        beanMap.forEach((i,j)-> objects.add(j));
        return objects;
    }

}

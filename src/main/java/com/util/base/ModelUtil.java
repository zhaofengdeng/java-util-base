package com.util.base;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelUtil {

	public static <T> T copy(T oldModel) {
		T _form = null;
		try {
			if (_form == null) {
				_form = (T) oldModel.getClass().newInstance();
			}

			PropertyDescriptor propertyDescriptor;
			Field[] fields = _form.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					propertyDescriptor = new PropertyDescriptor(field.getName(), _form.getClass());// 创建一个属性描述器
					Object val = propertyDescriptor.getReadMethod().invoke(oldModel);
					propertyDescriptor.getWriteMethod().invoke(_form, val);
				} catch (Exception e) {
					System.out.println("modelUtil错误,===================" + field.getName() + "============异常");
				}
			}
			return _form;

		} catch (Exception e) {
			e.printStackTrace();
			return _form;
		}
	}

	/**
	 * map转换成model.通过内省的方式
	 * 
	 * @param map
	 * @param clazz
	 * @param id
	 * @return
	 */
	public static <T> T toModel(Map<String, Object> map, Class<T> clazz) {
		T _form = null;
		try {
			if (_form == null) {
				_form = clazz.newInstance();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return _form;
		}

		PropertyDescriptor propertyDescriptor;
		Field[] fields = _form.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				// 创建一个属性描述器
				propertyDescriptor = new PropertyDescriptor(field.getName(), _form.getClass());
				if (map.containsKey(field.getName())) {
					assignValue(propertyDescriptor, field, _form, map.get(field.getName()));
				}
			} catch (Exception e) {
				if (field.getName().indexOf("_") != 0) {
					System.out.println("modelUtil错误,===================" + field.getName() + "============异常");
					e.printStackTrace();
				}

			}
		}
		return _form;

	}

	/**
	 * 通过内省的方式给指定的方式指定的对象赋值
	 * 
	 * @param propertyDescriptor
	 * @param field
	 * @param model
	 * @param value
	 */
	public static void assignValue(PropertyDescriptor propertyDescriptor, Field field, Object model, Object value) {
		try {
			if (value == null) {
				return;
			}
			Object formatValue = value;
			if (field.getType().getName().equals("java.lang.String")) {
				formatValue = value.toString();
			} else if (field.getType().getName().equals("java.lang.Long")) {
				formatValue = Long.parseLong(value.toString());
			} else if (field.getType().getName().equals("java.lang.Integer")) {
				formatValue = IntUtil.parseInt(value.toString());
			} else if (field.getType().getName().equals("java.lang.Float")) {
				formatValue = Float.valueOf(value.toString());
			} else if (field.getType().getName().equals("java.lang.Double")) {
				formatValue = Double.valueOf(value.toString());
			} else if (field.getType().getName().equals("java.lang.Boolean")) {
				if ("true".equals(value) || "1".equals(value)) {
					formatValue = true;
				} else {
					formatValue = false;
				}
				try {
					if (!(Boolean) formatValue) {
						formatValue = Boolean.parseBoolean(value.toString());
					}
				} catch (Exception e) {
				}
			} else if (field.getType().getName().equals("java.util.Date")) {
				formatValue = DateUtil.parseTimestamp(value.toString());
			} else if (field.getType().getName().equals("java.math.BigDecimal")) {
				formatValue = new BigDecimal(value.toString());
				// 转list
			} else if (field.getType().equals(List.class)) {
				List<Map<String, Object>> mapList = (List<Map<String, Object>>) value;
				Type genericType = field.getGenericType();
				if (null == genericType) {
					return;
				}
				if (genericType instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) genericType;
					// 得到泛型里的class类型对象
					List<Object> curEleList = new ArrayList<>();
					Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
					for (Map<String, Object> map : mapList) {
						Object listModel = toModel(map, actualTypeArgument);
						curEleList.add(listModel);
					}
					formatValue = curEleList;

				}
			}
			propertyDescriptor.getWriteMethod().invoke(model, formatValue);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package com.example.vollerydemo.custom.jsonutils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonParser {
	public <T> ObjectBean<T> json2Bean(String json, Class<T> clazz) {
		Gson gson = GsonFactory.getGson();
		Type objectType = type(ObjectBean.class, clazz);
		ObjectBean<T> beanWrap = null;
		try {
			beanWrap = gson.fromJson(json, objectType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			beanWrap = new ObjectBean<T>();
		}
		return beanWrap;
	}

	public <T> ParameterizedType type(final Class<T> raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}

	public <T> ObjectListBean<T> json2List(String json, Class<T> clazz) {
		Gson gson = GsonFactory.getGson();
		Type objectType = listType(ObjectListBean.class, clazz);
		ObjectListBean<T> listWrap = null;
		try {
			listWrap = gson.fromJson(json, objectType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			listWrap = new ObjectListBean<T>();
		}
		return listWrap;
	}

	public <T> ParameterizedType listType(final Class<T> raw,
			final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}

}

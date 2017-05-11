package com.csjbot.robot.biz.base.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.csjbot.robot.biz.base.exception.ServiceException;
import com.csjbot.robot.biz.base.exception.UtilException;

public class RequestUtil {
    private static final Logger log = Logger.getLogger(RequestUtil.class);
    static final String KW_DASH = "-";

    public static Object fetchParameter(Class<?> clazz,
            HttpServletRequest request) throws UtilException {
        Object object = null;
        try {
            object = clazz.newInstance();
            if (object == null)
                return null;

            fetchClassFieldParams(object, clazz, request);
        } catch (InstantiationException e) {
            throw new UtilException(e);
        } catch (IllegalAccessException e) {
            throw new UtilException(e);
        } catch (IntrospectionException e) {
            throw new UtilException(e);
        } catch (IllegalArgumentException e) {
            throw new UtilException(e);
        } catch (InvocationTargetException e) {
            throw new UtilException(e);
        }

        return object;
    }

    public static void fetchClassFieldParams(Object object, Class<?> clazz,
            HttpServletRequest request) throws IntrospectionException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if ((clazz == null) || (clazz.isPrimitive()))
            return;

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            Type type = field.getGenericType();
            if (Modifier.isStatic(field.getModifiers()))
                continue;
            String fieldName = field.getName();
            String valueStr = request.getParameter(fieldName);
            if (valueStr == null)
                continue;
            Object value = processValue(type, valueStr);

            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName,
                    clazz);
            Method method = descriptor.getWriteMethod();
            if (method == null)
                continue;
            method.invoke(object, new Object[] { value });
        }

        fetchClassFieldParams(object, clazz.getSuperclass(), request);
    }

    public static Object fetchParameter(Object object,
            MultipartHttpServletRequest request) throws UtilException {
        try {
            if (object == null)
                return null;
            if (object.getClass().isPrimitive())
                throw new UtilException("Unsupported object class '"
                        + object.getClass().getName() + "'.");

            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                Type type = field.getGenericType();
                if (!(Modifier.isStatic(field.getModifiers()))) {
                    String fieldName = field.getName();
                    String valueStr = request.getParameter(fieldName);
                    if (valueStr == null)
                        continue;
                    Object value = processValue(type, valueStr);

                    PropertyDescriptor descriptor = new PropertyDescriptor(
                            fieldName, object.getClass());
                    Method method = descriptor.getWriteMethod();
                    if (method == null)
                        continue;
                    method.invoke(object, new Object[] { value });
                }
            }
        } catch (IllegalAccessException e) {
            throw new UtilException(e);
        } catch (IntrospectionException e) {
            throw new UtilException(e);
        } catch (IllegalArgumentException e) {
            throw new UtilException(e);
        } catch (InvocationTargetException e) {
            throw new UtilException(e);
        }

        return object;
    }

    public static Object fetchParameter(Class<?> clazz, int index,
            HttpServletRequest request) throws UtilException {
        Object object = null;
        try {
            object = clazz.newInstance();
            if (object == null)
                return null;

            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                Type type = field.getGenericType();
                if (!(Modifier.isStatic(field.getModifiers()))) {
                    String fieldName = field.getName();
                    String tag = clazz.getSimpleName() + "-" + index + "-"
                            + fieldName;
                    String valueStr = request.getParameter(tag);
                    if (valueStr == null)
                        continue;
                    Object value = processValue(type, valueStr);

                    PropertyDescriptor descriptor = new PropertyDescriptor(
                            fieldName, clazz);
                    Method method = descriptor.getWriteMethod();
                    if (method == null)
                        continue;
                    method.invoke(object, new Object[] { value });
                }
            }
        } catch (InstantiationException e) {
            throw new UtilException(e);
        } catch (IllegalAccessException e) {
            throw new UtilException(e);
        } catch (IntrospectionException e) {
            throw new UtilException(e);
        } catch (IllegalArgumentException e) {
            throw new UtilException(e);
        } catch (InvocationTargetException e) {
            throw new UtilException(e);
        } catch (Exception e) {
            throw new UtilException(e);
        }

        return object;
    }

    public static void fetchClassFieldParams(Object object, Class<?> clazz,
            int index, HttpServletRequest request)
            throws IntrospectionException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        if ((clazz == null) || (clazz.isPrimitive()))
            return;

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            Type type = field.getGenericType();
            if (!(Modifier.isStatic(field.getModifiers()))) {
                String fieldName = field.getName();
                String tag = clazz.getSimpleName() + "_" + index + "_"
                        + fieldName;
                String valueStr = request.getParameter(tag);
                if (valueStr == null)
                    continue;
                Object value = processValue(type, valueStr);

                PropertyDescriptor descriptor = new PropertyDescriptor(
                        fieldName, clazz);
                Method method = descriptor.getWriteMethod();
                if (method == null)
                    continue;
                method.invoke(object, new Object[] { value });
            }
        }
        fetchClassFieldParams(object, clazz.getSuperclass(), index, request);
    }

    protected static Object processValue(Type type, String rawValue) {
        Object value = new Object();

        if (type.toString().equals(String.class.toString()))
            value = rawValue;
        else if (type.toString().equals(Boolean.TYPE.toString()))
            value = Boolean.valueOf(StringUtil.bool(rawValue));
        else if (type.toString().equals(Double.class.toString())) {
            try {
                value = Double.valueOf(Double.parseDouble(rawValue));
            } catch (Exception e) {
                value = null;
                if (log.isDebugEnabled())
                    log.debug(
                            "Failed to parse Double type value : '" + rawValue
                                    + "', set default 'null' value instead.", e);
            }
        } else if (type.toString().equals(Double.TYPE.toString())) {
            try {
                value = Double.valueOf(Double.parseDouble(rawValue));
            } catch (Exception e) {
                value = Double.valueOf(-1.0D);
                if (log.isDebugEnabled()) {
                    log.debug("Failed to parse double type value : '"
                            + rawValue + "', set default value '-1' instead.",
                            e);
                }
            }
        } else if (type.toString().equals(Float.class.toString())) {
            try {
                value = Float.valueOf(Float.parseFloat(rawValue));
            } catch (Exception e) {
                value = null;
                if (log.isDebugEnabled())
                    log.debug("Failed to parse Float type value : '" + rawValue
                            + "', set default 'null' value instead.", e);
            }
        } else if (type.toString().equals(Float.TYPE.toString())) {
            try {
                value = Float.valueOf(Float.parseFloat(rawValue));
            } catch (Exception e) {
                value = Float.valueOf(-1.0F);
                if (log.isDebugEnabled()) {
                    log.debug("Failed to parse float type value : '" + rawValue
                            + "', set default value '-1' instead.", e);
                }
            }
        } else if (type.toString().equals(Integer.class.toString())) {
            try {
                value = Integer.valueOf(Integer.parseInt(rawValue));
            } catch (Exception e) {
                value = null;
                if (log.isDebugEnabled())
                    log.debug(
                            "Failed to parse Integer type value : '" + rawValue
                                    + "', set default 'null' value instead.", e);
            }
        } else if (type.toString().equals(Integer.TYPE.toString())) {
            try {
                if (StringUtil.isEmpty(rawValue))
                    rawValue = "-1";
                value = Integer.valueOf(Integer.parseInt(rawValue));
            } catch (Exception e) {
                value = Integer.valueOf(-1);
                if (log.isDebugEnabled())
                    log.debug("Failed to parse int type value : '" + rawValue
                            + "', set default value '-1' instead.", e);
            }
        } else if (type.toString().equals(Long.class.toString())) {
            try {
                value = Long.valueOf(Long.parseLong(rawValue));
            } catch (Exception e) {
                value = null;
                if (log.isDebugEnabled())
                    log.debug("Failed to parse Long type value : '" + rawValue
                            + "', set default 'null' value instead.", e);
            }
        } else if (type.toString().equals(Long.TYPE.toString())) {
            try {
                value = Long.valueOf(Long.parseLong(rawValue));
            } catch (Exception e) {
                value = Long.valueOf(-1L);
                if (log.isDebugEnabled())
                    log.debug("Failed to parse long type value : '" + rawValue
                            + "', set default value '-1' instead.", e);
            }
        } else if (type.toString().equals(Short.class.toString())) {
            try {
                value = Short.valueOf(Short.parseShort(rawValue));
            } catch (Exception e) {
                value = null;
                if (log.isDebugEnabled())
                    log.debug("Failed to parse Short type value : '" + rawValue
                            + "', set default 'null' value instead.", e);
            }
        } else if (type.toString().equals(Short.TYPE.toString())) {
            try {
                value = Short.valueOf(Short.parseShort(rawValue));
            } catch (Exception e) {
                value = Integer.valueOf(-1);
                if (log.isDebugEnabled()) {
                    log.debug("Failed to parse short type value : \""
                            + rawValue + "\", set default value '-1' instead.",
                            e);
                }
            }
        } else if (type.toString().equals(BigDecimal.class.toString())) {
            try {
                value = new BigDecimal(rawValue);
            } catch (Exception e) {
                value = new BigDecimal(0);
                if (log.isDebugEnabled()) {
                    log.debug("Failed to parse double type value : '"
                            + rawValue + "', set default value '-1' instead.",
                            e);
                }

            }

        } else if (type.toString().equals(Date.class.toString())) {
            value = DateUtil.String2Date(rawValue);
        }

        return value;
    }

    public static String getDeviceID(HttpServletRequest request) {
        String meid = "";
        try {
            JSONObject jsonObj = new JSONObject(
                    request.getHeader("clientBaseInfo"));
            meid = jsonObj.getJSONObject("phoneinfo").getString("meid");
            if (meid.equals("0"))
                meid = "";
        } catch (Exception e) {
        }
        return meid;
    }

    public static String getAuthorization(HttpServletRequest request)
            throws ServiceException {
        String authorization = "";
        authorization = request.getHeader("Authorization");
        if ((authorization == null) || (authorization == "")){
            throw new ServiceException("Failed to get Authorization.");
        }
        authorization = authorization.replace("Basic ", "");
        return authorization;
    }

    public static HttpSession getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session == null)
            return null;

        return session;
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtil.isEmpty(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtil.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRefer(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (StringUtil.isEmpty(referer)) {
            referer = "";
        }
        return referer;
    }
}

package com.fx.xzt.sfserver.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author: tianliya
 * @date: 2018/10/30
 * @description:
 */
public class XmlConvert<T> {
    /**
     * 日志记录对象。
     */
    private Logger logger = LoggerFactory.getLogger(XmlConvert.class);

    /**
     * 实体类型
     */
    private Class<T> entityClass = null;

    /**
     * 构造函数。
     *
     * @param entityClass 实体类型
     */
    public XmlConvert(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * 将XML节点转换为对象(该对象必须配置@XmlRootElement属性)。
     *
     * @param
     * @return 转换后的对象
     */
    @SuppressWarnings({"unchecked"})
    public  T unmarshal(String xml) {
        if (StringUtils.isBlank(xml)) {
            return null;
        }
        StringReader reader = null;
        try {
            JAXBContext jaxbCtx = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            reader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}

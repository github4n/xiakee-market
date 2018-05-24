package com.xiakee.web.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 这个类是spring创建bean时给bean取名字的generator，在springmvc的配置文件中使用。
 * <p/>
 * <context:component-scan base-package="com.jd.bdp.web" use-default-filters="false"
 * name-generator="com.jd.bdp.common.spring.BdpAnnotationBeanNameGenerator">
 * <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
 * <context:include-filter type="annotation"
 * expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
 * </context:component-scan>
 * <p/>
 * <p/>
 * <p>
 * <context:component-scan base-package="com.jd.bdp.service.dqm" name-generator="com.jd.bdp.common.spring.BdpAnnotationBeanNameGenerator"  />
 * </p>
 * User: yfwangbing
 * Date: 13-11-13
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class XiakeeAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    // <context:component-scan base-package="com.jd.bdp.service.dqm" name-generator="com.jd.bdp.common.spring.BdpAnnotationBeanNameGenerator"  />
    //
    // OR
    // 
    // <context:component-scan base-package="com.jd.bdp.web" use-default-filters="false"
    //             name-generator="com.jd.bdp.common.spring.BdpAnnotationBeanNameGenerator">
    // </context:compont-scan>
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        return definition.getBeanClassName();
    }
}

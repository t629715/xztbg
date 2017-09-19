package com.fx.xzt.template;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
 * 
* @ClassName: CustomFreeMarkerConfigurer 
* @Description: 编写自己的freemarker配置 
* @author jcwang
* @date 2017年7月31日 下午4:00:30 
*
 */
public class CustomFreeMarkerConfigurer extends FreeMarkerConfigurer{
	
	/**
	 * 重写，在ftl模板页面加上[#escape x as x?html]
	 * **加上后ftl标签失效。。。
	 */
	/*@Override  
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {  
        return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));  
    }*/

	/**
	 * 重写导入shiro标签
	 */
	@Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}

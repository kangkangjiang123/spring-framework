/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.context;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.Nullable;

/**
 * 集合了配置上下文和web上下文，拥有了配置功能和环境功能
 * Interface to be implemented by configurable web application contexts.
 * Supported by {@link ContextLoader} and
 * {@link org.springframework.web.servlet.FrameworkServlet}.
 *
 * <p>Note: The setters of this interface need to be called before an
 * invocation of the {@link #refresh} method inherited from
 * {@link org.springframework.context.ConfigurableApplicationContext}.
 * They do not cause an initialization of the context on their own.
 *
 * @author Juergen Hoeller
 * @since 05.12.2003
 * @see #refresh
 * @see ContextLoader#createWebApplicationContext
 * @see org.springframework.web.servlet.FrameworkServlet#createWebApplicationContext
 */
public interface ConfigurableWebApplicationContext extends WebApplicationContext, ConfigurableApplicationContext {

	/**
	 * Prefix for ApplicationContext ids that refer to context path and/or servlet name.
	 */
	String APPLICATION_CONTEXT_ID_PREFIX = WebApplicationContext.class.getName() + ":";

	/**
	 * Name of the ServletConfig environment bean in the factory.
	 * @see javax.servlet.ServletConfig
	 */
	String SERVLET_CONFIG_BEAN_NAME = "servletConfig";


	/**
	 * 为此Web应用程序上下文设置ServletContext
	 * Set the ServletContext for this web application context.
	 * 不会导致上下文初始化：在设置所有配置属性之后需要自己调用刷新。
	 * <p>Does not cause an initialization of the context: refresh needs to be
	 * called after the setting of all configuration properties.
	 * @see #refresh()
	 */
	void setServletContext(@Nullable ServletContext servletContext);

	/**
	 * 为此Web应用程序上下文设置ServletConfig。
	 * Set the ServletConfig for this web application context.
	 * 仅对属于特定servlet的WebApplicationContext调用
	 * Only called for a WebApplicationContext that belongs to a specific Servlet.
	 * @see #refresh()
	 */
	void setServletConfig(@Nullable ServletConfig servletConfig);

	/**
	 * 返回此Web应用程序上下文的ServletConfig（如果有）
	 * Return the ServletConfig for this web application context, if any.
	 */
	@Nullable
	ServletConfig getServletConfig();

	/**
	 * 设置这个上下文的命名空间
	 * Set the namespace for this web application context,
	 * 用于构建默认上下文配置位置
	 * to be used for building a default context config location.
	 * root web application context没有命名空间
	 * The root web application context does not have a namespace.
	 */
	void setNamespace(@Nullable String namespace);

	/**
	 * 返回这个上下文的命名空间
	 * Return the namespace for this web application context, if any.
	 */
	@Nullable
	String getNamespace();

	/**
	 * 以init-param样式设置此Web应用程序上下文的配置位置，即用逗号、分号或空格分隔不同的位置。
	 * Set the config locations for this web application context in init-param style,
	 * i.e. with distinct locations separated by commas, semicolons or whitespace.
	 * <p>If not set, the implementation is supposed to use a default for the
	 * given namespace or the root web application context, as appropriate.
	 */
	void setConfigLocation(String configLocation);

	/**
	 * 设置此Web应用程序上下文的配置集合位置
	 * Set the config locations for this web application context.
	 * <p>If not set, the implementation is supposed to use a default for the
	 * given namespace or the root web application context, as appropriate.
	 */
	void setConfigLocations(String... configLocations);

	/**
	 * 返回此Web应用程序上下文的配置集合
	 * Return the config locations for this web application context,
	 * or {@code null} if none specified.
	 */
	@Nullable
	String[] getConfigLocations();

}

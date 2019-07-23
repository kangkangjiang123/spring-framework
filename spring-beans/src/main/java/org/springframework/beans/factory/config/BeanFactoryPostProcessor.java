/*
 * Copyright 2002-2016 the original author or authors.
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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * 允许自定义修改应用程序上下文的bean definition
 * Allows for custom modification of an application context's bean definitions,
 * 调整bean工厂中的对象属性值
 * adapting the bean property values of the context's underlying bean factory.
 *
 * 应用程序上下文可以自动检测bean定义中的BeanFactoryPostProcessor 对象，并在创建任何其他bean之前应用它们。
 * <p>Application contexts can auto-detect BeanFactoryPostProcessor beans in
 * their bean definitions and apply them before any other beans get created.
 *
 * 适用于针对覆盖在应用程序上下文中配置的bean属性的系统管理员的自定义配置文件。
 * <p>Useful for custom config files targeted at system administrators that
 * override bean properties configured in the application context.
 *
 * 有关解决此类配置需求的开箱即用解决方案，请参见PropertyResourceConfigurer及其具体实现。
 * <p>See PropertyResourceConfigurer and its concrete implementations
 * for out-of-the-box solutions that address such configuration needs.
 *
 * BeanFactoryPostProcessor可以与bean definition交互并修改bean definition，
 * 但不能与bean实例交互。这样做可能会导致过早的bean实例化，破坏容器并导致意想不到的副作用。
 * 如果需要bean实例交互，则考虑实现{@link BeanPostProcessor}。
 * <p>A BeanFactoryPostProcessor may interact with and modify bean
 * definitions, but never bean instances. Doing so may cause premature bean
 * instantiation, violating the container and causing unintended side-effects.
 * If bean instance interaction is required, consider implementing
 * {@link BeanPostProcessor} instead.
 *
 * @author Juergen Hoeller
 * @since 06.07.2003
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 * 在应用程序上下文的标准初始化之后修改其内部bean工厂。
	 * 所有bean定义都已加载，但还没有实例化bean。这允许覆盖或添加属性，甚至是对提前暴露的bean。
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}

/*
 * Copyright 2012-2022 the original author or authors.
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

package org.springframework.boot;

import java.time.Duration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * Listener for the {@link SpringApplication} {@code run} method.
 * {@link SpringApplicationRunListener}s are loaded via the {@link SpringFactoriesLoader}
 * and should declare a public constructor that accepts a {@link SpringApplication}
 * instance and a {@code String[]} of arguments. A new
 * {@link SpringApplicationRunListener} instance will be created for each run.
 *
 * @author Phillip Webb
 * @author Dave Syer
 * @author Andy Wilkinson
 * @author Chris Bono
 * @since 1.0.0
 */
// 用于表达 SpringBoot 启动各个阶段，有别于 ApplicationListener
// 需要在 META-INF/spring.factories 配置才能生效
public interface SpringApplicationRunListener {

	/**
	 * Called immediately when the run method has first started. Can be used for very
	 * early initialization.
	 * @param bootstrapContext the bootstrap context
	 */
	// run 方法首次启动时立即调用。可用于非常早的初始化
	default void starting(ConfigurableBootstrapContext bootstrapContext) {
	}

	/**
	 * Called once the environment has been prepared, but before the
	 * {@link ApplicationContext} has been created.
	 * @param bootstrapContext the bootstrap context
	 * @param environment the environment
	 */
	// 准备好环境，但在创建 {@link ApplicationContext} 之前调用
	default void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
			ConfigurableEnvironment environment) {
	}

	/**
	 * Called once the {@link ApplicationContext} has been created and prepared, but
	 * before sources have been loaded.
	 * @param context the application context
	 */
	// 创建并准备好 {@link ApplicationContext}，但在加载源之前调用。
	default void contextPrepared(ConfigurableApplicationContext context) {
	}

	/**
	 * Called once the application context has been loaded but before it has been
	 * refreshed.
	 * @param context the application context
	 */
	// 加载好应用程序上下文但在刷新之前调用
	default void contextLoaded(ConfigurableApplicationContext context) {
	}

	/**
	 * The context has been refreshed and the application has started but
	 * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
	 * ApplicationRunners} have not been called.
	 * @param context the application context.
	 * @param timeTaken the time taken to start the application or {@code null} if unknown
	 * @since 2.6.0
	 */
	// 上下文已刷新，应用程序已启动，但尚未调用 {@link CommandLineRunner CommandLineRunners}
	// 和 {@link ApplicationRunner ApplicationRunners}
	default void started(ConfigurableApplicationContext context, Duration timeTaken) {
	}

	/**
	 * Called immediately before the run method finishes, when the application context has
	 * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
	 * {@link ApplicationRunner ApplicationRunners} have been called.
	 * @param context the application context.
	 * @param timeTaken the time taken for the application to be ready or {@code null} if
	 * unknown
	 * @since 2.6.0
	 */
	// 在 run 方法完成之前立即调用，当应用程序上下文已刷新并且所有 {@link CommandLineRunner CommandLineRunners}
	// 和 {@link ApplicationRunner ApplicationRunners} 已被调用。
	default void ready(ConfigurableApplicationContext context, Duration timeTaken) {
	}

	/**
	 * Called when a failure occurs when running the application.
	 * @param context the application context or {@code null} if a failure occurred before
	 * the context was created
	 * @param exception the failure
	 * @since 2.0.0
	 */
	// 运行应用程序失败时调用。 @param context 应用程序上下文或 {@code null} 如果在创建上下文之前发生故障
	default void failed(ConfigurableApplicationContext context, Throwable exception) {
	}

}

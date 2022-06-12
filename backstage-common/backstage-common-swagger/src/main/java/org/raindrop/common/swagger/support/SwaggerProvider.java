package org.raindrop.common.swagger.support;

import lombok.RequiredArgsConstructor;
import org.raindrop.common.swagger.config.SwaggerProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sywd 聚合接口文档注册，和zuul实现类似
 */
@Primary
@Component
@RequiredArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

	private static final String API_URI = "/v2/api-docs";

	private final RouteDefinitionRepository routeDefinitionRepository;

	private final SwaggerProperties swaggerProperties;

	@Override
	public List<SwaggerResource> get() {

		if (swaggerProperties==null || !swaggerProperties.getEnabled())
		{
			return null;
		}
		List<RouteDefinition> routes = new ArrayList<>();
		routeDefinitionRepository.getRouteDefinitions().subscribe(routes::add);
		return routes.stream()
				.flatMap(routeDefinition -> routeDefinition.getPredicates().stream()
						.filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
						.filter(predicateDefinition -> !swaggerProperties.getIgnoreProviders()
								.contains(routeDefinition.getId()))
						.map(predicateDefinition -> swaggerResource(routeDefinition.getId(),
								predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**",
										API_URI))))
				.sorted(Comparator.comparing(SwaggerResource::getName)).collect(Collectors.toList());
	}

	private static SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}

}

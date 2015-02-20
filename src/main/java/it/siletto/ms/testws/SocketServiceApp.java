package it.siletto.ms.testws;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import it.siletto.ms.base.bundle.AtmosphereBundle;
import it.siletto.ms.base.health.BasicHealthCheck;
import it.siletto.ms.testws.resources.BaseResource;


public class SocketServiceApp extends Application<AppConfiguration> {

	public static void main(final String[] args) throws Exception {
		new SocketServiceApp().run(args);
	}

	@Override
	public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/site", "/site"));
		bootstrap.addBundle(new AtmosphereBundle("it.siletto.ms.testws.resources.socket", "10", "/testws/*"));
	}

	@Override
	public void run(final AppConfiguration backendConfiguration, final Environment environment) throws Exception {
		environment.jersey().register(new BaseResource());
		environment.healthChecks().register("TestHealthCheck", new BasicHealthCheck());
	}
}

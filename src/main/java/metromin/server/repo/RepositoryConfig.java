package metromin.server.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import metromin.server.model.Campaign;
import metromin.server.model.Case;
import metromin.server.model.Message;
import metromin.server.model.User;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Case.class, Campaign.class, Message.class, User.class);
    }

}

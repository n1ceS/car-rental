package pl.mcm.carrental.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableOpenApi
@EnableSwagger2
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

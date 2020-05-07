package com.allanweber.checkcode.report.config;

import com.allanweber.checkcode.github.services.GitHubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitHubClientConfig {

    @Bean
    public GitHubClient getClient() {
        return new GitHubClient();
    }
}

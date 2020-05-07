package com.allanweber.checkcode.github.services;

import com.allanweber.checkcode.github.dto.Languages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GitHubClientTest {

    private GitHubClient client = new GitHubClient();

    @Test
    public void testLanguages() {
        Languages languages = client.getRepositoryLanguages("allanweber", "accounts").block();

        assertNotNull(languages.getLanguages());
    }

}
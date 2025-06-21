package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.TmdbMovieDto;
import com.example.miniNetflix.domain.dto.TmdbSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@Service
public class TMDbClient {

    @Value("${tmdb.api.token}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private final String Base_Url = "https://api.themoviedb.org/3/search/movie";

    public TMDbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Optional<TmdbMovieDto> searchMovieByTitle(String title){
        String url = UriComponentsBuilder.fromHttpUrl(Base_Url).queryParam("query",title).queryParam("api_key",apiKey).build().toUriString();
        ResponseEntity<TmdbSearchResponse> response = restTemplate.getForEntity(url, TmdbSearchResponse.class);
        if(response.getStatusCode().is2xxSuccessful()&& response.getBody() != null && !response.getBody().getResults().isEmpty()){
            return Optional.of(response.getBody().getResults().get(0));
        }
        return Optional.empty();
    }
}

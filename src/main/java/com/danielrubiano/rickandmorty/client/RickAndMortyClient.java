package com.danielrubiano.rickandmorty.client;

import com.danielrubiano.rickandmorty.response.CharacterReponse;
import com.danielrubiano.rickandmorty.response.EpisodeResponse;
import com.danielrubiano.rickandmorty.response.ListOfEpisodesResponse;
import com.danielrubiano.rickandmorty.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterReponse> findById(String id) {
        log.info("Buscando personaje con id [{}]", id);
        return webClient
                .get()
                .uri("/character/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique los parametros")))
                .bodyToMono(CharacterReponse.class);
    }

    public Mono<LocationResponse> findALocationById(String id) {
        log.info("Buscando localizacion con id [{}]", id);
        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique los parametros")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findAEpisodeById(String id) {
        log.info("Buscando episodio con id [{}]", id);
        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique los parametros")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> ListAllEpisodes() {
        log.info("Listando todos los episodios");
        return webClient
                .get()
                .uri("/episode")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique los parametros")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }

}

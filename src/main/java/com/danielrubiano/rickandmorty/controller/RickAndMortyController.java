package com.danielrubiano.rickandmorty.controller;

import com.danielrubiano.rickandmorty.client.RickAndMortyClient;
import com.danielrubiano.rickandmorty.response.CharacterReponse;
import com.danielrubiano.rickandmorty.response.EpisodeResponse;
import com.danielrubiano.rickandmorty.response.ListOfEpisodesResponse;
import com.danielrubiano.rickandmorty.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CharacterReponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findById(id);
    }

    @GetMapping("/location/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findALocationById(id);
    }

    @GetMapping("/episode/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findAEpisodeById(id);
    }

    @GetMapping("/episodes")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ListOfEpisodesResponse> ListAllEpisodes() {
        return rickAndMortyClient.ListAllEpisodes();
    }
}

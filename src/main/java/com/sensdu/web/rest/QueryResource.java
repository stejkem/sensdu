package com.sensdu.web.rest;


import javax.inject.Inject;
import javax.validation.Valid;
import com.codahale.metrics.annotation.Timed;

import com.sensdu.domain.Query;
import com.sensdu.repository.QueryRepository;
import com.sensdu.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QueryResource {

    private final Logger log = LoggerFactory.getLogger(QueryResource.class);

    @Inject
    private QueryRepository queryRepository;

    /**
     * POST  /queries : Save a user's query to the database
     *
     * @param query save user's query to the database
     * @return the ResponseEntity with status 201 (Created) and with body the new query, or with status 400 (Bad Request) if the query has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/queries",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Query> createQuery(@Valid @RequestBody Query query) throws URISyntaxException {
        log.debug("REST request to save Query : {}", query);
        if (query.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("query", "idexists", "A new query cannot already have an ID")).body(null);
        }
        Query result = queryRepository.save(query);
        return ResponseEntity.created(new URI("/api/queries/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("query", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /queries : Updates an existing query.
     *
     * @param query the query to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated query,
     * or with status 400 (Bad Request) if the query is not valid,
     * or with status 500 (Internal Server Error) if the query couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/queries",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Query> updateQuery(@Valid @RequestBody Query query) throws URISyntaxException {
        log.debug("REST request to update Query : {}", query);
        if (query.getId() == null) {
            return createQuery(query);
        }
        Query result = queryRepository.save(query);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("query", query.getId().toString()))
                .body(result);
    }

    /**
     * GET  /queries : get all the queries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of queries in body
     */
    @RequestMapping(value = "/queries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Query> getAllQueries() {
        log.debug("REST request to get all Queries");
        List<Query> queries = queryRepository.findAll();
        return queries;
    }

    /**
     * GET  /queries/:id : get the "id" query.
     *
     * @param id the id of the query to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the query, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/queries/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Query> getQuery(@PathVariable Long id) {
        log.debug("REST request to get Query : {}", id);
        Query query = queryRepository.findOne(id);
        if (!(query == null)) {
            return new ResponseEntity<>(query, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE  /queries/:id : delete the "id" query.
     *
     * @param id the id of the query to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/queries/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteQuery(@PathVariable Long id) {
        log.debug("REST request to delete Query : {}", id);
        queryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("query", id.toString())).build();
    }

}



package com.sensdu.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * A table query that stores queries, date of request and result.
 */
@Entity
@Table(name = "query")
public class Query implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotNull
//    @Column(name = "date", nullable = false)
//    private LocalDate date;
}

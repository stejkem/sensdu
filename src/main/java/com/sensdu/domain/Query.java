package com.sensdu.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * A table query that stores queries, date of request and result.
 */
@Entity
@Table(name = "query")
public class Query implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "query", nullable = false)
    private String query;

    @NotNull
    @Column(name = "query_wiki_url", nullable = false)
    private String queryWikiURL;

    @NotNull
    @Column(name = "translation", nullable = false)
    private String translation;

    @NotNull
    @Column(name = "translation_wiki_url", nullable = false)
    private String translationWikiURL;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getQuery() {
        return query;
    }

    public String getQueryWikiURL() {
        return queryWikiURL;
    }

    public String getTranslation() {
        return translation;
    }

    public String getTranslationWikiURL() {
        return translationWikiURL;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setQueryWikiURL(String queryWikiURL) {
        this.queryWikiURL = queryWikiURL;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setTranslationWikiURL(String translationWikiURL) {
        this.translationWikiURL = translationWikiURL;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", date='" + date + "'" +
                ", query='" + query + "'" +
                ", queryWikiURL='" + queryWikiURL + "'" +
                ", translation='" + translation + "'" +
                ", translationWikiURL='" + translationWikiURL + "'" +
                '}';
    }
}

package com.sensdu.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A table sourceWord that stores queries, date of request, userAget info and results.
 */
@Entity
@Table(name = "user_query")
public class Query implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Nullable
//    @Column(name = "date")
//    private LocalDate date;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "from_language")
    private String fromLanguage;

    @Column(name = "to_language")
    private String toLanguage;

    @Column(name = "source_word")
    private String sourceWord;

    @Column(name = "source_word_wiki_url")
    private String sourceWordWikiUrl;

    @Column(name = "translated_word")
    private String translatedWord;

    @Column(name = "translated_word_wiki_url")
    private String translatedWordWikiUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAget(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    public String getSourceWordWikiUrl() {
        return sourceWordWikiUrl;
    }

    public void setSourceWordWikiUrl(String sourceWordWikiUrl) {
        this.sourceWordWikiUrl = sourceWordWikiUrl;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translation) {
        this.translatedWord = translation;
    }

    public String getTranslatedWordWikiUrl() {
        return translatedWordWikiUrl;
    }

    public void setTranslatedWordWikiUrl(String translationWikiURL) {
        this.translatedWordWikiUrl = translationWikiURL;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
//                ", date='" + date + "'" +
                ", userAgent='" + userAgent + "'" +
                ", fromLanguage='" + fromLanguage + "'" +
                ", toLanguage='" + toLanguage + "'" +
                ", sourceWord='" + sourceWord + "'" +
                ", sourceWordWikiUrl='" + sourceWordWikiUrl + "'" +
                ", translatedWord='" + translatedWord + "'" +
                ", translatedWordWikiUrl='" + translatedWordWikiUrl + "'" +
                '}';
    }
}

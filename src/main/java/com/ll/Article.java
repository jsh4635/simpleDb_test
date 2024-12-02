package com.ll;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Article {

    /**
     * id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     *                     PRIMARY KEY(id),
     *                     createdDate DATETIME NOT NULL,
     *                     modifiedDate DATETIME NOT NULL,
     *                     title VARCHAR(100) NOT NULL,
     *                     `body` TEXT NOT NULL,
     *                     isBlind BIT(1) NOT NULL DEFAULT 0
     */

    private int id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime modifiedDate;

    private String title;
    private String body;

    private boolean isBlind;


}

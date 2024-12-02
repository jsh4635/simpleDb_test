package com.ll;

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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String title;
    private String body;
    private boolean isBlind;


}

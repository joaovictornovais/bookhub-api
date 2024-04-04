package br.com.joao.library.domain.email;

import lombok.Getter;

@Getter
public enum StatusEmail {

    SENT("sent"),
    ERROR("error");

    private final String status;

    private StatusEmail(String status) {
        this.status = status;
    }

}

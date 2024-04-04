package br.com.joao.library.domain.email;

public record EmailDTO(String ownerRef, String emailFrom, String emailTo, String subject, String text) {
}

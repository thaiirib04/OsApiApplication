/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.dev.thaissa.OSApiApplication.domain.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author sesi3dib
 */
public record ComentarioDTO(
        @NotBlank(message = "A descricao do comentario e obrigatoria")
        String descricao) {
        
}

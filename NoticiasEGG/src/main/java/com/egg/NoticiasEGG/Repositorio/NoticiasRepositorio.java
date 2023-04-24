/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.NoticiasEGG.Repositorio;

import com.egg.NoticiasEGG.Entidad.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JessyAriEXO
 */

@Repository
public interface NoticiasRepositorio extends JpaRepository<Noticia,String> {
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo =:titulo")
    public Noticia buscarPorTitulo(@Param("titulo")String titulo);
    
}

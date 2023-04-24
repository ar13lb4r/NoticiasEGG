/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.NoticiasEGG.Servicio;

import com.egg.NoticiasEGG.Entidad.Noticia;
import com.egg.NoticiasEGG.Excepcion.MiException;
import com.egg.NoticiasEGG.Repositorio.NoticiasRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JessyAriEXO
 */

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiasRepositorio noticiasRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo,String cuerpo) throws MiException{
        
        validarAtributos(titulo, cuerpo);
        
        Noticia noticia=new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        
        noticiasRepositorio.save(noticia);
        
    }
    
    public List<Noticia>listarNoticias(){
        List<Noticia> noticias = new ArrayList();
        noticias = noticiasRepositorio.findAll();
        return noticias;
    }
    
        @Transactional
        public void modificarNoticia(String id,String titulo,String cuerpo) throws MiException{
            
            
            Optional<Noticia> respuesta= noticiasRepositorio.findById(id);
            if(respuesta.isPresent()){
                Noticia noticia = respuesta.get();
                
                noticia.setTitulo(titulo);
                noticia.setCuerpo(cuerpo);
                
                noticiasRepositorio.save(noticia);
               
            }else{
                throw new MiException("no se encontro la noticia");
            }
        }
            
            public Noticia getOne(String id){
                return noticiasRepositorio.getOne(id);
            }
            
            public void eliminarNoticia(String titulo) throws MiException{
                
                Noticia noticiaEncontrada = noticiasRepositorio.buscarPorTitulo(titulo);
                
                validarAtributos(noticiaEncontrada.getTitulo(),noticiaEncontrada.getCuerpo());
                
                noticiasRepositorio.delete(noticiaEncontrada);
            }
            
            private void validarAtributos(String titulo,String cuerpo) throws MiException{
                
                if(titulo.isEmpty()||titulo==null){
                    throw new MiException("el titulo no puede estar vacio");
                }
                if(cuerpo.isEmpty()||cuerpo==null){
                    throw new MiException("El cuerpo no puede estar vacio");
                }
            }
    
    }
package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Libro> findAll(){
        return libroRepository.findAll();
    }

    @Override
    public Libro findOne(int id){
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.orElse(null);
    }
    @Override
    public Libro save(Libro libro){
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(int id,  int idCategoria, int idAutor, Libro libro){
        Libro libroExistente = findOne(id);

        Optional<Autor> autorExistente = autorRepository.findById(idAutor);
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);

        if (libroExistente == null){
            return null;
        }

        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setEditorial(libro.getEditorial());
        libroExistente.setNumPaginas(libro.getNumPaginas());
        libroExistente.setEdicion(libro.getEdicion());
        libroExistente.setIdioma(libro.getIdioma());
        libroExistente.setFechaPublicacion(libro.getFechaPublicacion());
        libroExistente.setDescripcion(libro.getDescripcion());
        libroExistente.setTipoPasta(libro.getTipoPasta());
        libroExistente.setIsbn(libro.getIsbn());
        libroExistente.setNumEjemplares(libro.getNumEjemplares());
        libroExistente.setPortada(libro.getPortada());
        libroExistente.setPresentacion(libro.getPresentacion());
        libroExistente.setPrecio(libro.getPrecio());
        libroExistente.setAutor(libro.getAutor());
        libroExistente.setCategoria(libro.getCategoria());

        return libroRepository.save(libroExistente);

    }

    @Override
    public void delete(int id){
        if (libroRepository.existsById(id)){
            libroRepository.deleteById(id);
        }
    }

}

package com.example.demo.controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.Pelicula;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controler {

    public static int position = -1;
    public static int contador = 0;
    public final String fileName = "movies.csv";
    public static ArrayList<Pelicula> peliculasDB = new ArrayList<>();

    public Controler() {
        this.loadingFilms(fileName);
    }

    @GetMapping("/")
    public List<Pelicula> index() {
        return Controler.peliculasDB;

    }//

    @GetMapping("/list/{order}")
    public List<Pelicula> order(@PathVariable String order) {
        Comparator<Pelicula> comparador = Comparator.comparing(Pelicula::getFilm);
        List<Pelicula> listaPrincipal = peliculasDB.stream().sorted(comparador).collect(Collectors.toList());

        return listaPrincipal;

    }//

    @PostMapping("/save/{id}")
    public Pelicula save(@PathVariable int id, @RequestBody Pelicula pelicula) {
        pelicula.id = id;
        Controler.peliculasDB.add(pelicula);
        return pelicula;
    }

    @GetMapping("/movie/{id}")
    public Pelicula find(@PathVariable int id) {
        this.locate(id);
        if (position < 0) {
            return null;
        } else {
            Pelicula contacto = Controler.peliculasDB.get(position);
            position = -1;
            return contacto;
        }
    }

    @PostMapping("/update/{id}")
    public Pelicula update(@PathVariable int id, @RequestBody Pelicula pelicula) {
        pelicula.id = id;
        this.locate(id);
        if (position < 0) {
            return null;
        } else {
            Controler.peliculasDB.set(position, pelicula);
            position = -1;
            return pelicula;
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        this.locate(id);
        if (position < 0) {
            return "no found for removed";
        } else {
            Controler.peliculasDB.remove(position);
            position = -1;
            return "removed " + id;
        }
    }

    public void locate(int id) {
        Controler.peliculasDB.forEach(data -> {
            if (data.id == id) {
                position = contador;
            }
            contador++;
        });
        contador = 0;
    }

    public void loadingFilms(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                // separating the data by the comma
                String[] detailed = currentLine.split(",");

                // skiping the first line since it has only titles and no the data I want to
                // store
                if (detailed[0].equals("id"))
                    continue;

                // Storing data in variables

                int id = Integer.valueOf(detailed[0]);
                String film = detailed[1];
                String genre = detailed[2];
                String studio = detailed[3];
                int score = Integer.valueOf(detailed[4]);
                int year = Integer.valueOf(detailed[5]);

                // adding the data to the arraylist

                peliculasDB.add(new Pelicula(id, film, genre, studio, score, year));

            }

        } catch (IOException ioe) {

            ioe.printStackTrace();

        }
    }

}

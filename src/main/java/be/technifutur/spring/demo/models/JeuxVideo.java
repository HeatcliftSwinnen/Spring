package be.technifutur.spring.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JeuxVideo {
    private int UniqueId;
    private String name;
    private List<String> genre;
    private LocalDate dateDeSortie;
    private String nomStudio;
    private double prix;
    private List<String> platform;
}

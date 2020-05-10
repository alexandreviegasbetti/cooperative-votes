package br.com.cooperative.vote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "associate")
public class Associate {

    @Id
    @Column(name = "id_associate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "cpf", nullable = true)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "associate")
    private List<Vote> votes;
}

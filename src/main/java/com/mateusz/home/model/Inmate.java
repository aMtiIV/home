package com.mateusz.home.model;

import com.mateusz.home.command.CreateInmateCommand;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "inmates")
@EqualsAndHashCode
public class Inmate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "surname")
    @NonNull
    private String surname;

    @Setter
    @ManyToOne
    @JoinColumn(name = "home_id")
    private Home home;

    public Inmate(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static Inmate from(CreateInmateCommand inmate) {
        return Inmate.builder()
                .name(inmate.getName())
                .surname(inmate.getSurname())
                .build();
    }

    public void update(CreateInmateCommand inmate) {
        this.name = inmate.getName();
        this.surname = inmate.getSurname();
    }
}

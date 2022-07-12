package com.mateusz.home.model;

import com.mateusz.home.command.CreateHomeCommand;
import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "homes")
@EqualsAndHashCode(exclude = "inmates")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    @NonNull
    private String address;

    @OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
    private Set<Inmate> inmates = new HashSet<>();

    public Home(String address) {
        this.address = address;
    }

    public static Home from(CreateHomeCommand home) {
        return Home.builder()
                .address(home.getAddress())
                .build();
    }

    @PreRemove
    private void preRemove() {
        for (Inmate inmate : inmates) {
            inmate.setHome(null);
        }
    }
}

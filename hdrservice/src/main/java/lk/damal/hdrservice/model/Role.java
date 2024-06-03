package lk.damal.hdrservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long roleId;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Role> roles = new ArrayList<>();
}

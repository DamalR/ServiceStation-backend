package lk.damal.hdrservice.dto;

import lk.damal.hdrservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @Getter
    @Setter
    private Long roleId;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private List<Role> roles = new ArrayList<>();
}

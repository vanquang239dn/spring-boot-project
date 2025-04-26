package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {

    // DI : dependency injection
    private final RoleRepository roleRepository;

    public Role handleFindRoleByRoleName(String name) {
        return roleRepository.findByName(name);
    }

}

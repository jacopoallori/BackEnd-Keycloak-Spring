package it.jacopo.keycloak.demo_backend.mapper;

import it.jacopo.keycloak.demo_backend.dto.KeycloakRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.external.KeycloakRoleExternalDTO;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//*
//Mapper per DTO KeycloakRoleExternalDTO e KeycloakRoleDTO
//*
@Configuration
public class RoleUserDTOMapper {
    public List<KeycloakRoleDTO> fromDTOExternalToDTO(List<KeycloakRoleExternalDTO> dto) {
        List<KeycloakRoleDTO> output = new ArrayList<>();
        for (KeycloakRoleExternalDTO keycloakRoleExternalDTO : dto){
            KeycloakRoleDTO keycloakUserDTO = new KeycloakRoleDTO();
            keycloakUserDTO.setId(keycloakRoleExternalDTO.getId());
            keycloakUserDTO.setName(keycloakRoleExternalDTO.getName());
            keycloakUserDTO.setDescription(keycloakRoleExternalDTO.getDescription());
            keycloakUserDTO.setComposite(keycloakUserDTO.getComposite());
            keycloakUserDTO.setClientRole(keycloakRoleExternalDTO.getClientRole());
            keycloakUserDTO.setContainerId(keycloakUserDTO.getContainerId());
            output.add(keycloakUserDTO);
        }
        return output;
    }
}

package it.jacopo.keycloak.demo_backend._old.mapper;

import it.jacopo.keycloak.demo_backend._old.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend._old.dto.external.KeycloakUserExternalDTO;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//*
//Mapper per DTO KeycloakUserExternalDTO e KeycloakUserDTO
//*
@Configuration
public class UserDTOMapper {
    public List<KeycloakUserDTO> fromDTOExternalToDTO(List<KeycloakUserExternalDTO> dto) {
        List<KeycloakUserDTO> output = new ArrayList<>();
        for (KeycloakUserExternalDTO keycloakUserExternalDTO : dto){
            KeycloakUserDTO keycloakUserDTO = new KeycloakUserDTO();
            keycloakUserDTO.setId(keycloakUserExternalDTO.getId());
            keycloakUserDTO.setUsername(keycloakUserExternalDTO.getUsername());
            keycloakUserDTO.setEmail(keycloakUserExternalDTO.getEmail());
            keycloakUserDTO.setEnabled(keycloakUserExternalDTO.getEnabled());
            output.add(keycloakUserDTO);
        }

        return output;
    }
}
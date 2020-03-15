package com.ps.landing.project.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.models.PSUser;
//import com.puntosingular.base.models.User;
//import com.puntosingular.base.models.User;

@Component("UserConverter")
public class UserConverter {

	public PSUser UserDTOtoUser(UserDTO userDTO) {

        PSUser user = new PSUser();
	/*
        List<SubCatalogDTO> subCatalogDTOS = dto.getSubCatalogs();
        List<SubCatalog> subCatalogs = new ArrayList<>();
        */

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword( userDTO.getPassword());
        user.setStatus(userDTO.isStatus());
        user.setRegistrationDate( userDTO.getRegistrationDate());
        user.setUpdateDate( userDTO.getUpdateDate());
        user.setRoles(userDTO.getRoles());
        

        /*for(SubCatalogDTO subCatalogDTO : subCatalogDTOS) {

            SubCatalog subCatalog = new SubCatalog();

            subCatalog.setId(subCatalogDTO.getId());
            subCatalog.setName(subCatalogDTO.getName());
            subCatalog.setDescription(subCatalogDTO.getDescription());
            subCatalog.setParent(dto.getId());
            subCatalog.setStatus(subCatalogDTO.isStatus());
            subCatalog.setCreateDate((Timestamp) subCatalogDTO.getCreateDate());
            subCatalog.setLastUpdateDate((Timestamp) subCatalogDTO.getLastUpdateDate());

            subCatalogs.add(subCatalog);
        }
        catalog.setSubCatalogs(subCatalogs);*/

        return user;
    }

    /*public List<Catalog> convertToModel(List<CatalogDTO> dtoList) {

        List<Catalog> catalogs = new ArrayList<>();

        for(CatalogDTO catalogDTO : dtoList) {

            Catalog catalog = convertToModel(catalogDTO);
            catalogs.add(catalog);
        }

        return catalogs;
    }*/

	public UserDTO UsertoUserDTO(PSUser user) {

		

			UserDTO userDTO = new UserDTO();

			 userDTO.setId(user.getId());
			 userDTO.setName(user.getName());
			 userDTO.setUsername(user.getUsername());
			 userDTO.setLastname(user.getLastname());
			 userDTO.setEmail(user.getEmail());
			 userDTO.setPassword( user.getPassword());
			 userDTO.setStatus(user.isStatus());
			 userDTO.setRegistrationDate(user.getRegistrationDate());
			 userDTO.setUpdateDate( user.getUpdateDate());
			 userDTO.setRoles(user.getRoles());
			return userDTO;
		
	}

   
    
    /*public List<UserDTO> UserstoUsersDTO(Iterable<PSUser> users) {

		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (PSUser user : users) {

			UserDTO userDTO = new UserDTO();

			user.setId(user.getId());
			 userDTO.setName(user.getName());
			 userDTO.setUsername(user.getUsername());
			 userDTO.setLastname(user.getLastname());
			 userDTO.setEmail(user.getEmail());
			 userDTO.setPassword( user.getPassword());
			 userDTO.setStatus(user.isStatus());
			 userDTO.setRegistrationDate((Timestamp) user.getRegistrationDate());
			 userDTO.setUpdateDate((Timestamp) user.getUpdateDate());
			 userDTO.setRoles(user.getRoles());
		}
		return usersDTO;
	}*/
    
    public List<UserDTO> convertToDTO(List<PSUser> users) {

        List<UserDTO> userDTOS = new ArrayList<>();

        for(PSUser user : users) {

            UserDTO userDTO = UsertoUserDTO(user);
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }
}

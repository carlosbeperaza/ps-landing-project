package com.ps.landing.project.auth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private UserService service;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		PSUser PSUser = service.findByFirstName(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		
		try {
			info.put("nombre", PSUser.getName());
			info.put("apellido", PSUser.getLastname());
			info.put("email", PSUser.getEmail());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}

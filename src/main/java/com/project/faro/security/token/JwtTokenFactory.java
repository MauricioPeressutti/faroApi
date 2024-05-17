package com.project.faro.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.faro.entity.Person;
import com.project.faro.security.config.JwtSettings;
import com.project.faro.security.entity.User;

import javax.annotation.Resource;
import javax.crypto.ExemptionMechanismException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenFactory {
	@Resource
	private JwtSettings jwtSettings;

	public JwtToken createTokenOne(User user, TokenType tokenType) {

		Claims claims = Jwts.claims().setSubject(user.getUsername());

//			claims.put("scopes", operator);

		if (tokenType == TokenType.REFRESH) {
			claims.put("tokenType", JwtSettings.REFRESH_TOKEN);
		} else {
			user.setPassword(null);
			claims.put("scopes", user);
		}

		user.setPerson(null);

		LocalDateTime currentTime = LocalDateTime.now();

//        long tokenExpirationTime = (tokenType == TokenType.AUTH) ? jwtSettings.getTokenExpirationTime() : jwtSettings.getRefreshTokenExpTime();

		String token = Jwts.builder().setClaims(claims).setIssuer(jwtSettings.getTokenIssuer())
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				// .setExpiration(Date.from(currentTime
				// .plusMinutes(tokenExpirationTime)
				// .atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, JwtSettings.SIGNING_KEY).compact();

		return new JwtToken(token, claims);
	}

	public JwtToken createPersonToken(Person person) {
		Claims claims = Jwts.claims().setSubject(person.getDocNumber());
		claims.put("scopes", person);
		// claims.put("hasReportCard",
		// reportCardPPService.personHasReportCard(person.getId()));
		LocalDateTime currentTime = LocalDateTime.now();
		long tokenExpirationTime = jwtSettings.getPersonTokenExpirationTime();

		String token = Jwts.builder().setClaims(claims).setIssuer(jwtSettings.getTokenIssuer())
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date
						.from(currentTime.plusMinutes(tokenExpirationTime).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, JwtSettings.SIGNING_KEY).compact();

		return new JwtToken(token, claims);

	}
	
	public User getUserByToken(String token) throws JsonMappingException, JsonProcessingException, ExemptionMechanismException {
       
        String[] tokenParts = token.split("\\.");

        String encodedPayload = tokenParts[1];
        byte[] decodedPayload = Base64.getUrlDecoder().decode(encodedPayload);
        String payloadString = new String(decodedPayload);
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(payloadString);

            // Extraer los campos del nodo "scopes"
            JsonNode scopesNode = rootNode.get("scopes");

            // Crear un nuevo objeto User y establecer sus campos
            User user = new User();
            user.setId(scopesNode.get("id").asInt());
            user.setUsername(scopesNode.get("username").asText());
            user.setPassword(scopesNode.get("password").asText());
            user.setIsOwner(scopesNode.get("isOwner").asBoolean());
            user.setDefaultPass(scopesNode.get("defaultPass").asBoolean());

            // Imprimir el objeto User
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExemptionMechanismException("Error");
        }
        
    }
}

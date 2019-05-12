package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.Profesor;
import cl.usach.apitesis.repository.ProfesorRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/get_token")
public class TokenService {

    @Autowired
    ProfesorRepository profesorRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Profesor login(@RequestParam("firebaseID") String firebaseID) {
        Profesor profesor = this.profesorRepository.findProfesorByFirebaseUID(firebaseID);
        if (profesor == null) return null;
        String token = getJWTToken(String.format("%s %s %s", profesor.getNombreProfesor(),
                profesor.getApellidoPaterno(),
                profesor.getApellidoMaterno()));
        profesor.setToken(token);
        return profesor;
    }

    private String getJWTToken(String username) {
        String secretKey = "wXS0H40YR-7iJl2qP5GJRExhZH1mScKovNPVVnsPW1V19ChIJiURtpkcJRDUsRW2vi4-i0v3tww2TJ4O2cD5v7Oo5cq8clh3OY25OIXZ44uG9BLUzh4Id9l-RT6f7jv1oSYF65zLuAN1qD6pDXJdw3z8ZOI-gCWoWY-x3oKUjrA1WdTjiAx5GDuvnRxtVSenfaCy8gSzqRy0SyqVLdqxIpJa8bwyjkyeiHi0MJgsZrDuZc327ATlfjV1A9vSFS-NwPMwlkPNNOeVgt-csFrUOpOAMfHWyirO2z9xvd3pQvdB0jFA5jsRFlYsNHiwWMDj6kHooAZU4JMH1C2tF0yyOg";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("APITesis")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}

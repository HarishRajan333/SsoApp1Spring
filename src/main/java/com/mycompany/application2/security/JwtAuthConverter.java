package com.mycompany.application2.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Autowired
    private JwtAuthConvterProperties properties;

    public JwtAuthConverter(JwtAuthConvterProperties properties) {
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> autho = Stream.concat(jwtGrantedAuthoritiesConverter.convert(source).stream(), extractResourceRoles(source).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, autho);
    }

//    String getPrincipalClaimName(Jwt jwt) {
//        String claimName = JwtClaimNames.SUB;
//        if (properties.getPrincipalAttribute() != null) {
//            claimName = properties.getPrincipalAttribute();
//        }
//        return jwt.getClaim(claimName);
//    }
    Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        String desiganition = jwt.getClaim("Designation");

        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId());
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
        resourceRoles.add((String) desiganition);
        if (resourceAccess == null || resource == null || resourceRoles == null) {
            return Set.of();
        }
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toSet());
    }

}

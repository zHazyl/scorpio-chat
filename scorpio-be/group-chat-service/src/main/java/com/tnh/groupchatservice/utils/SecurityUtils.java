package com.tnh.groupchatservice.utils;

import com.auth0.jwt.JWT;
import com.tnh.groupchatservice.config.KeycloakProvider;

public class SecurityUtils {

    public static String getCurrentUser() {
//        var principal = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return principal.getId();
        var token = UserContextHolder.getContext().getAuthToken().replace("Bearer ", "");
        var decode = JWT.decode(token);
        var id = decode.getSubject();
        return id;
    }

    public static String getCurrentUserPreferredUsername(KeycloakProvider keycloakProvider) {
//        var principal = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return principal.getUsername();
        var id = getCurrentUser();
        var usersResource = keycloakProvider.getInstance().realm(keycloakProvider.getRealm()).users().get(id);
        var username = usersResource.toRepresentation().getUsername();
        return username;
    }

}

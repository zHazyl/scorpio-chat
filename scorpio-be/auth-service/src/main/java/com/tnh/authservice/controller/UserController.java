package com.tnh.authservice.controller;

import com.auth0.jwt.JWT;
import com.tnh.authservice.config.KeycloakProvider;
import com.tnh.authservice.domain.User;
import com.tnh.authservice.dto.UserDTO;
import com.tnh.authservice.mapper.UserMapper;
import com.tnh.authservice.messaging.sender.UserSender;
import com.tnh.authservice.model.AuthRequestModel;
import com.tnh.authservice.model.TokenResponse;
import com.tnh.authservice.service.KeycloakAdminClientService;
import com.tnh.authservice.service.UserService;
import com.tnh.authservice.utils.exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final KeycloakAdminClientService keycloakAdminClientService;
    private final KeycloakProvider keycloakProvider;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserDTO userDTO,
                                                 UriComponentsBuilder uriComponentsBuilder) {
        try {
            keycloakAdminClientService.createKeycloakUser(userDTO);
        } catch(Exception e) {
            throw new AlreadyExistsException("User is exist");
        }

        Keycloak keycloak = keycloakProvider.newKeycloakBuilderWithPasswordCredentials(userDTO.getUsername(), userDTO.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        String token = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            token = accessTokenResponse.getToken();
        } catch (Exception e) {

        }
        var decode = JWT.decode(token);
        var id = decode.getSubject();
        try {
            userService.getUserById(id);
        } catch (Exception e){
            userDTO.setId(id);
//            userSender.send(userDTO);
            userService.voting(userDTO);

            var location = uriComponentsBuilder.path("/users/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).body(userDTO);
        }
//        var user = userService.createUser(id, userDTO.getUsername(), userDTO.getPassword(),
//                userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName());
        throw new RuntimeException();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> login(@NotNull @RequestBody AuthRequestModel authRequestModel) {

        TokenResponse tokenResponse = null;
        try {
            Keycloak keycloak = keycloakProvider.newKeycloakBuilderWithPasswordCredentials(authRequestModel.getUsername(), authRequestModel.getPassword()).build();

            AccessTokenResponse accessTokenResponse = null;
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            tokenResponse = new TokenResponse(accessTokenResponse.getToken());
            return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenResponse);
        }
    }
    @GetMapping (path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") String id) {
        UserDTO user = userMapper.mapToUserDTO(userService.getUserById(id));
        log.debug(user.getEmail());
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> editUser(@PathVariable("id") String userId, @RequestBody UserDTO userDTO) {
        var user = userService.modifyUser(userId, userDTO.getFirstName(), userDTO.getLastName());
        return ResponseEntity.ok(userMapper.mapToUserDTO(user));
    }

//    @PutMapping(path = "/{id}")
//    public boolean updateUserById(@PathVariable("id") Long id,
//                                  @RequestParam(required = false) String password_hash,
//                                  @RequestParam(required = false) String first_name,
//                                  @RequestParam(required = false)String last_name,
//                                  @RequestParam(required = false)String email){
//        return userService.updateUserById(id, password_hash, first_name, last_name, email);
//    }

    @DeleteMapping (path = "/{id}")
    public void deleteUserById(@PathVariable("id") String id){
        userService.deleteUserById(id);
    }
}

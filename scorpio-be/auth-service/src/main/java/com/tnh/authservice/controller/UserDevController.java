package com.tnh.authservice.controller;

import com.tnh.authservice.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserDevController {

    @PostMapping("/authenticate")
    //UserDTO
    public ResponseEntity<String> login(@NotNull @RequestBody String authRequestModel) {
        //Goi keycloak server lay token, roi tra ve cho fe
        String tokenResponsehan = "{\"access_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxT1FRWUxkcVhMRnBJdUl2WkhpUm5meEtld1FTd1U1OWFFMjBCMWZnYnhVIn0.eyJleHAiOjE2ODM2MzU3OTYsImlhdCI6MTY4MzYxNzc5NiwianRpIjoiZTA1ZGUwMmItZDBmOS00ZDRjLTllOWEtZmIyOTU4OTJjNTYwIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL3Njb3JwaXVzIiwic3ViIjoiMDVmMmMxNDUtNTY5YS00NDExLTgwNGUtMGI4NjczYzEzZTcxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZnJvbnRlbmQiLCJzZXNzaW9uX3N0YXRlIjoiZWY0ZWI0MDEtZTMwNS00OTdiLWFmMDgtMzM5YjAwYzViM2VlIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImVmNGViNDAxLWUzMDUtNDk3Yi1hZjA4LTMzOWIwMGM1YjNlZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiaGFuIn0.DiJ7OyFp2WaeIxg65Spp9QhHpSLdK4kRKRnbwrU9O3tB9-tLMr40VqBOlHEUQ7OWMVNYh8lNmQWLgjaMKQdyuOjolpT83amRc7c_SbYeHGx4xP8XlsdLMfd6YYUwNKZomiwdolCA66WFgF3k4olHg-gjNplRen49CBSvMHOtCiLpGKpWsgDVBPS-MKMiY9cGmy7plXDg8vBY_wTG4sDP6WeFDSrjcWFWK-_a-SZVAbnoe3dM4JmftgfVLGQm2b8O94XUiHqLa2silG98h5HNql94ZVcFN5dF4YLA8FR-YC1ZCtmd-_Dkj3S2h8q743L9CUYLgkJVb8fzpBqEd1JE3Q\"}";
        String tokenRepsonsehanh = "{\"access_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxT1FRWUxkcVhMRnBJdUl2WkhpUm5meEtld1FTd1U1OWFFMjBCMWZnYnhVIn0.eyJleHAiOjE2ODM2MzU3MTcsImlhdCI6MTY4MzYxNzcxNywianRpIjoiMmMwNjNmY2QtN2FjNS00MDM5LTk0OTQtMDAxNTQxMDRkOTUxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL3Njb3JwaXVzIiwic3ViIjoiYjQ5ODRmZGMtYzdiMy00NmE3LTg3NzUtNjliOTY0MDlkM2EzIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZnJvbnRlbmQiLCJzZXNzaW9uX3N0YXRlIjoiNDc3NDE2NTItZjA4ZC00ZWJhLWJiN2UtMWU1YmQyOTMwOWJjIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjQ3NzQxNjUyLWYwOGQtNGViYS1iYjdlLTFlNWJkMjkzMDliYyIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiaGFuaCIsImVtYWlsIjoiaGFuQGguY29tIn0.IJuGwa5iC7pCywEJGsgIssOXiCrHNN4fwIVhD7mfRVeKLszVdkKNVtH745tameZ5pcZHmjb9CNIbCzpWc5OJE1Xs8SxQbRfivXbAxX-nUKpR6WjzzSuXmWnJQ3_U_5SdLK66LfnxK-JcHEuRci0Da1Wf3kC17mvuqniHZ9bSW42BMexn8GunkRn8sbS-To5Pj3oHioidTsjoY1lbD9doKh0kEdVEnAyyYyY_1evIy0Ql0bqmfGmAYBGGL6Hhw_Xka1DjgVzFT_6S7Q6jQ6nEYDpCV8h5WKzSFefn1o2tqMgQqrzGD_GCZTX99XJlzIZ2hSaXKrpMHx0vK7FJz0F5ww\"}";
        return ResponseEntity.status(HttpStatus.OK).body(authRequestModel.contains("hanh") ? tokenRepsonsehanh : tokenResponsehan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") String userId) {
        String userDTO;
        log.debug(userId);
        if (userId.equals("05f2c145-569a-4411-804e-0b8673c13e71")) {
            userDTO =
                    "{" +
                    "\"id\": \"05f2c145-569a-4411-804e-0b8673c13e71\"," +
                    "\"username\": \"han\"," +
                    "\"firstName\": \"Gia\"," +
                    "\"lastName\": \"Han\"," +
                    "\"email\": \"g@han.com\"" +
                    "}";
        } else {
            userDTO =
                    "{" +
                    "\"id\": \"b4984fdc-c7b3-46a7-8775-69b96409d3a3\"," +
                    "\"username\": \"hanh\"," +
                    "\"firstName\": \"N\"," +
                    "\"lastName\": \"H\"," +
                    "\"email\": \"han@h.com\"" +
                    "}";
        }
        // goi xuong db lay user sau do map qua userdto

        return ResponseEntity.ok(userDTO);
    }

}

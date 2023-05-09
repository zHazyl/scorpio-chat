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
        String tokenResponsehan = "{\"access_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxT1FRWUxkcVhMRnBJdUl2WkhpUm5meEtld1FTd1U1OWFFMjBCMWZnYnhVIn0.eyJleHAiOjE2ODI3ODExOTgsImlhdCI6MTY4Mjc2MzE5OCwianRpIjoiNzhmZjU5NWQtN2JiZi00ZjFlLTk5ODUtNWEyZTBmNjc0ODdlIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL3Njb3JwaXVzIiwic3ViIjoiMDVmMmMxNDUtNTY5YS00NDExLTgwNGUtMGI4NjczYzEzZTcxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZnJvbnRlbmQiLCJzZXNzaW9uX3N0YXRlIjoiMzdhNDMxZWQtMmU5NC00ZmViLTliNjctNjQyZjcxNmJhOWRmIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjM3YTQzMWVkLTJlOTQtNGZlYi05YjY3LTY0MmY3MTZiYTlkZiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiaGFuIn0.FjZ7u8Gz9hLoS8CLfrUwVxOD7M0mLM03F3v3hIqC0yn5-gnuaJ8wWt38mmWAQdBn-RxplajtOXqzQxGydMCgpeeGBgZ54Dnc1TV5KOObopUW7X3kwig1SEVRDs766nDKePqT_uIuJqtEBOpFihedc713bkH0l062nc2CqA_MrfSygCmuQTMlbued0SXVQV7qQR9-u6kcQtzBsGBd8DynoVu6DwJ9CR5wVCeM38kzs1r0n8q0awJTA0G3QQ7bp3hdKYHMHU7fSGumInkAeayJnDSlRUVm3C5c17sv93wAPFfQnXu5PEnlIST2baaiSmfzsrf-OJ_vFGIcFgHJS_u7DQ\"}";
        String tokenRepsonsehanh = "{\"access_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxT1FRWUxkcVhMRnBJdUl2WkhpUm5meEtld1FTd1U1OWFFMjBCMWZnYnhVIn0.eyJleHAiOjE2ODI3ODU5MDgsImlhdCI6MTY4Mjc2NzkwOCwianRpIjoiNWMyYmVkM2YtY2I3MC00ZDAwLWEwNTYtMTk0NzU3Nzc1ZjIwIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL3Njb3JwaXVzIiwic3ViIjoiYjQ5ODRmZGMtYzdiMy00NmE3LTg3NzUtNjliOTY0MDlkM2EzIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZnJvbnRlbmQiLCJzZXNzaW9uX3N0YXRlIjoiNjRlMWNjMjktM2ZlOS00MThiLThjOWEtZDFiOGM2YWIxODNhIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjY0ZTFjYzI5LTNmZTktNDE4Yi04YzlhLWQxYjhjNmFiMTgzYSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiaGFuaCIsImVtYWlsIjoiaGFuQGguY29tIn0.BLyvFVrA5ejZWtbTQXjqY2VNi0zFGH303U1aPmbQvDURnnDlvY7TF0gCapfG9pqoA5HLtwH0gJxtaVOukQns51SEm_j-_L_w1UG9TmGD79BX-JW_a1780MUXG4fbsJgM0Fz0s2KFYr2U81De6BggTaItPU1hIbbdXiBh1OWtkVLzNFv3NCmap08en2HXREmL5TCn9_uUS4r9pdNLEFE0khMpPbqY0Psd3hQ58plJqv4_VZU1jydEE5BatTnde4J7WRIlC84fihw0KghL6UGilQb0wKz2ydOfcz_S2AAnb2XLIa_ggTlR4iFOytCjMiWtF2lDXihOEb8t3n4CQqE9ow\"}";
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

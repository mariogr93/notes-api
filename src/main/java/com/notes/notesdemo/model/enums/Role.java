package com.notes.notesdemo.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.notes.notesdemo.model.enums.Permission.*;


@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_CREATE,
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE
          )
  );

  @Getter
  private final Set<Permission> permissions;

}

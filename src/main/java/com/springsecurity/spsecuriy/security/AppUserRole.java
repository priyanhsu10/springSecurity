package com.springsecurity.spsecuriy.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.springsecurity.spsecuriy.security.AppUserPermission.*;

public enum AppUserRole {
   STUDENT(Sets.newHashSet()),
   ADMIN(Sets.newHashSet(STUDENT_WRITE,STUDENT_READ,COURSE_WRITE,COURSE_READ)),
   TRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
   private final Set<AppUserPermission> permissions;

   AppUserRole(Set<AppUserPermission> permissions) {
      this.permissions = permissions;
   }

   public Set<AppUserPermission> getPermissions() {
      return permissions;
   }
   public Set<SimpleGrantedAuthority> getGrantedAuthorities(){

      Set<SimpleGrantedAuthority> permissions=  getPermissions().stream().map(x->new SimpleGrantedAuthority(x.getPermission()))
              .collect(Collectors.toSet());
   permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
   return permissions;
   }
}

package com.springsecurity.spsecuriy.security;

public enum AppUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    public String getPermission() {
        return permission;
    }

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }
}

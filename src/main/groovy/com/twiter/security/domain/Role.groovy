package com.twiter.security.domain

class Role {
    Role(String name){
        this.name = name
    }
    private String name

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}

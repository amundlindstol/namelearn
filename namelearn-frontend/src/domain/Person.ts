import React, { Component } from 'react';

interface Person {
    id: string;
    fullname: string;
    email: string;
    initial: string;
    manager: string;
    phone: string;
    department: string;
    location: string;
    position: string;
    src: string;
/*    constructor(id: string, name: string, email: string, initial: string, manager: string, phone: string, department: string, location: string, position: string, src: string) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.initial = initial;
        this.manager = manager;
        this.phone = phone;
        this.department = department;
        this.location = location;
        this.position = position;
        this.src = src;
    }*/
}

export default Person;

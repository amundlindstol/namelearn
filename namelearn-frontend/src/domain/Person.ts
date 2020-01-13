import React, { Component } from 'react';

class Person {
    id: string;
    name: string;
    email: string;
    initial: string;
    manager: string;
    phone: string;
    department: string;
    location: string;
    position: string;
    picSrc: string;
    static initial: string;
    constructor(id: string, name: string, email: string, initial: string, manager: string, phone: string, department: string, location: string, position: string, picsrc: string) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.initial = initial;
        this.manager = manager;
        this.phone = phone;
        this.department = department;
        this.location = location;
        this.position = position;
        this.picSrc = picsrc;
    }
}

export default Person;

import React, { Component } from 'react';
import Person from "./Person";

class Api {

    browse() {
        return fetch('http://localhost:6969/find-root')
            .then(res =>  res.json())
            .catch(err => { throw new Error(err) })
    }


    /*formatPerson(p: any) : Person {
        return { fullname: p.name, email: p.email, initial: p.initial, manager: p.manager, phone: p.phone, department: p.department, location: p.location, position: p.position, src: p.src, id: p.id };
        return { fullname: "p.name", email: "p.email", initial: "p.initial", manager: "p.manager", phone: "p.phone", department: "p.department", location: "p.location", position: "p.position", src: "p.picsrc", id: "p.id" };
    }*/

    formatPeople(pList) {
        let people = [];
        for (let i = 0; i < pList.length; i = i+1) {

        }
        return people;
    }
}

export default Api;

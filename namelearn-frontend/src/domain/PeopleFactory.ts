import React, { Component } from 'react';
import Person from "./Person";

class PeopleFactory {

    getRandom() : Promise<Person> {
        return fetch('http://localhost:6969/random')
            .then(res => res.json())
            .then(res => this.formatPerson(res))
            .catch(err => { throw new Error(err)} )
    }

    getAll() : Promise<Person[]>{
        return fetch('http://localhost:6969/all')
            .then(res => res.json())
            .then(res => res.map((person: any) => this.formatPerson(person)))
            .catch(err => { throw new Error(err) })
    }

    formatPerson(p: any) : Person {
        return { fullname: p.name, email: p.email, initial: p.initial, manager: p.manager, phone: p.phone, department: p.department, location: p.location, position: p.position, src: p.src, id: p.id };
        return { fullname: "p.name", email: "p.email", initial: "p.initial", manager: "p.manager", phone: "p.phone", department: "p.department", location: "p.location", position: "p.position", src: "p.picsrc", id: "p.id" };
    }

    formatPeople(pList: any[]) : Person[] {
        let people: Person[] = [];
        for (let i = 0; i < pList.length; i = i+1) {

        }
        return people;
    }
}

export default PeopleFactory;

import React, {Component} from 'react';
import Person from "../domain/Person";
import PeopleFactory from "../domain/PeopleFactory";

const download = require('image-downloader');


async function downloadImage(options: any) {
    try {
        const {filename, image} = await download.image(options);
        console.log(filename) // => /path/to/dest/image.jpg
    } catch (e) {
        console.error(e)
    }
}

function downloadAllImages(url: string) {
    const peopleFactory = new PeopleFactory();
    const people = peopleFactory.getAll().then((people: Person[]) => {
        for (let i = 0; i < people.length; i += 1) {
            let person: Person = people[i];
            let options = {
                url: url + person.src,
                dest: ''
            }
        }
    });
}
// Download to a directory and save with an another filename
/*
options = {
    url: 'http://someurl.com/image2.jpg',
    dest: '/path/to/dest/photo.jpg'      // Save to /path/to/dest/photo.jpg
}
*/

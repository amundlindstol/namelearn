import React, {Component} from 'react';
import Person from "../domain/Person";
import Api from "../domain/Api";

const download = require('image-downloader');

class GetPictures {

    async downloadImage(options) {
        try {
            const {filename, image} = await download.image(options);
            console.log(filename) // => /path/to/dest/image.jpg

        } catch (e) {
            console.error(e)
        }
    }

    downloadAllImages() {
        const peopleFactory = new Api();
        const people = peopleFactory.browse().then((people) => {
            for (let i = 0; i < people.length; i += 1) {
                let person = people[i];
                let options = {
                    url: person.src,
                    dest: '/src/resources/' + person.fullname + '.png'
                };
                this.downloadImage(options);
                break;
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
}

export default GetPictures;

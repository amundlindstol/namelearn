class Api {

    browse = (path) => {
        let b = {
            "path" : path,
            "sug_meg" : "b"
        };
        return fetch('http://localhost:6969/find-element', {
                method: 'POST',
                headers: {'Content-Type':'application/json'},
                body: JSON.stringify(b)
            })
            .then(res => {
                if(!res.ok) {
                    return res.json().then(text => "Invalid Path")
                }
                else {
                    return res.text();
                }
            })
            .catch(err => err);
    };

    root = (state) => {
        let b = {
            "path" : state.path,
            "attribute_name" : state.attributeName,
            "attribute_value" : state.attributeValue
        };
        return fetch('http://localhost:6969/find-root', {
                method: 'POST',
                headers: {'Content-Type':'application/json'},
                body: JSON.stringify(b)
            })
            .then(res => res.text())
            .catch(err => err.text());
    };

    select = (state) => {
        let b = {
            "name" : state.name,
            "path" : state.path,
            "attribute_name" : state.attributeName,
            "attribute_value" : state.attributeValue,
            "select_attribute" : state.selectAttribute

        };
        return fetch('http://localhost:6969/find-element-value', {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(b)
        })
            .then(res => {
                if(!res.ok) {
                    return res.json().then(text => console.log(text.message))
                }
                else {
                    return res.text();
                }
            })
            .catch(err => err);
    };

    build = (instructionList) => {
        var body = {};
        return fetch('http://localhost:6969/build-people', {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify("b")
        })
            .then(res => {
                if(!res.ok) {
                    return res.json().then(text => console.log(text.message))
                }
                else {
                    return res.json().then(r => console.log(r));
                }
            })
            .catch(err => err);
    };

}

export default Api;

class Api {

    connectToSite = (baseUrl, jSessionId) => {
        let body = {
            "base_url" : baseUrl,
            "j_session_id" : jSessionId
        };
        return fetch('http://localhost:6969/connect', {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(body)
        })
        .then(res => {
            if(!res.ok) {
                return res.json().then(text => "Invalid Path")
            }
            else {
                return res.json();
            }
        })
    };

    browse = (state) => {
        let b = {
            "path" : state.path,
            "attribute_name" : state.attributeName,
            "attribute_value" : state.attributeValue
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
                    return res.json();
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
            .then(res => res.json())
            .catch(err => err);
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
        let body = {
            "instruction_list": this.buildList(instructionList)
        };
        return fetch('http://localhost:6969/build-people', {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(body)
        })
            .then(res => {
                if(!res.ok) {
                    return res.json().then(text => console.log(text.message))
                }
                else {
                    return res.json();
                }
            })
            .catch(err => err);
    };

    buildList(instructionList) {
        let fetchList = [];
        instructionList.forEach(instruction => {
            fetchList.push(
                {
                    "name": instruction.name,
                    "path": instruction.path,
                    "attribute_name": instruction.attributeName,
                    "attribute_value": instruction.attributeValue,
                    "select_attribute": instruction.selectAttribute
                }
            );
        });
        return fetchList;
    }
}

export default Api;

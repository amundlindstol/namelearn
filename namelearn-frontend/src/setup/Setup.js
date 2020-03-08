import React, {Component} from 'react';
import '../css/App.css';
import '../css/Setup.css';
import Browser from "./Browser";
import Root from "./Root";
import Select from "./Select";

class Setup extends Component {

    //todo add select on btn click
    render() {
        return (
            <div className="App">
                <div className="Setup">
                    <Browser/>
                    <Root/>
                    <Select/>
                </div>
            </div>
        );
    }
}

export default Setup;

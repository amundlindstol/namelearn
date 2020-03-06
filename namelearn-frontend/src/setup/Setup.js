import React, {Component} from 'react';
import '../css/App.css';
import '../css/Setup.css';
import Browser from "./Browser";

class Setup extends Component {
    render() {
        return (
            <div className="App">
                <div className="Setup">
                    <Browser/>
                </div>
            </div>
        );
    }
}

export default Setup;

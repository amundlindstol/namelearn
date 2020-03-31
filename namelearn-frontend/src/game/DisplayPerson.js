import React, {Component} from "react";

class DisplayPerson extends Component {
    constructor(props) {
        super(props);
        this.state = {
            person: props.person,
            hidden: false
        };
    }

    render() {
        const hidden = this.state.hidden ? 'hidden' : '';
        return (
            <div>
                <div className={hidden} >
                    {this.state.person !== undefined ? (
                        <img src={"data:image/png;base64,"+this.state.person.picture}/>
                    ) : (
                        <div>loading</div>
                    )}
                </div>
                <div>Who is this?</div>
            </div>
        );
    }
}

export default DisplayPerson;

import React, {Component} from "react";
import Person from "../domain/Person";

class DisplayPerson extends Component {
    props = {
        person: Person,
        hidden: false
    };
    render() {
        const hidden = this.props.hidden ? 'hidden' : '';
        // @ts-ignore
        return (
            <div>
                <div className={hidden} >
                    {this.props.person.name}
                    {this.props.person.initial}
                </div>
                <div>Who is this?</div>
            </div>
        );
    }
}

export default DisplayPerson;

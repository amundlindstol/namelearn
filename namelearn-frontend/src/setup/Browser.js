import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";

class Browser extends Component {
    state = {
        path: '//div',
    };
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        console.log(event);
        event.preventDefault();
    }

    callBack = (formData) => {
        console.log(formData);
        this.setState({path: formData})
    };

    render() {
        return (
            <div className={"form"}>
                <InnerForm callBack={this.callBack} path={"//div"} text={"xPath"} />
                <input className={"submit"} type="submit" value="Submit" />

            </div>
        );
    }
}
export default Browser;

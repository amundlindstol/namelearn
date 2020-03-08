import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";
import Api from "../domain/Api";

class Select extends Component {
    state = {
        name: 'name',
        path: '//div//div//div//span',
        attributeName: 'class',
        attributeValue: 'profile-username',
        selectAttribute: 'title',
        result: 'nothing'
    };
    constructor(props) {
        super(props);
        this.api = new Api();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.api.select(this.state).then(res => this.setState({result: res})).catch(err => this.setState({result: err}));
    };

    callBack = (formData) => {
        this.setState({path: formData});
    };

    render() {
        return (
            <div className={"form-outer"}>
                <div className={"form"}>
                    <InnerForm callBack={this.callBack} text={"path"} />
                    <InnerForm text={"attribute name"} />
                    <InnerForm text={"attribute value"} />
                    <input className={"submit"} type="submit" value="Submit" onClick={this.handleSubmit} />
                </div>
                <pre>
                    {this.state.result}
                </pre>
            </div>
        );
    }
}
export default Select;

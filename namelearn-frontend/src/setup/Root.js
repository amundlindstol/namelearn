import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";
import Api from "../domain/Api";

class Root extends Component {
    state = {
        path: '//div',
        attributeName: 'class',
        attributeValue: 'profile-entry',
        result: 'nothing'
    };
    constructor(props) {
        super(props);
        this.api = new Api();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.api.root(this.state).then(res => this.setState({result: res})).catch(err => this.setState({result: err}));
    };

    callBack = (formData) => {
        this.setState({path: formData});
    };

    //todo remove
    componentDidMount() {
        this.api.root(this.state).then(res => this.setState({result : res}));
    }

    render() {
        return (
            <div className={"form-outer"}>
                <div className={"form"}>
                    <InnerForm callBack={this.callBack} text={"xPath"} />
                    <input className={"submit"} type="submit" value="Submit" onClick={this.handleSubmit} />
                </div>
                <pre>
                    {this.state.result}
                </pre>
            </div>
        );
    }
}
export default Root;

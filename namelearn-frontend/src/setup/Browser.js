import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";
import Api from "../domain/Api";

class Browser extends Component {
    constructor(props) {
        super(props);
        this.api = new Api();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            path: '',
            attributeName: '',
            attributeValue: '',
            relativePath: ''
        };
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.api.browse(this.state).then(res => this.setState({result: res.result, relativePath: res.relative_path})).catch(err => this.setState({result: err}));
    };

    callBack = (formData, type) => {
        switch (type) {
            case "path" :
                this.setState({path : formData}); break;
            case "attribute name" :
                this.setState({attributeName: formData}); break;
            case "attribute value" :
                this.setState({attributeValue: formData}); break;
            default:
                console.error("Form type not found: " + type);
        }
    };

    render() {
        return (
            <div className={"form-outer"}>
                <form className={"form"} >
                    <InnerForm callBack={this.callBack} text={"path"} />
                    <InnerForm callBack={this.callBack} text={"attribute name"} />
                    <InnerForm callBack={this.callBack} text={"attribute value"} />
                    <button className={"submit"} type={"submit"} onClick={this.handleSubmit}>submit</button>
                </form>
                <pre>
                    {"relative path: "+this.state.relativePath+"\n"}
                    {this.state.result}
                </pre>
            </div>
        );
    }
}
export default Browser;

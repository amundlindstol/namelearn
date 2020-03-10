import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";
import Api from "../domain/Api";

class Select extends Component {
    constructor(props) {
        super(props);
        this.api = new Api();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {result: 'nothing'};
        if (props.isDefault !== undefined) {
            this.state = {
                name: 'name',
                path: '/div/div',
                attributeName: 'class',
                attributeValue: 'profile-username',
                selectAttribute: 'title',
                result: 'nothing'
            };
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.api.select(this.state).then(res => {
            this.setState({result: res});
            this.props.callBack(this.state);
            return res;
        }).catch(err => this.setState({result: err}));
    };

    callbackFormData = (formData, type) => {
        switch (type) {
            case "name" :
                this.setState({name : formData}); break;
            case "path" :
                this.setState({path: formData}); break;
            case "attribute name" :
                this.setState({attributeName : formData}); break;
            case "attribute value" :
                this.setState({attributeValue : formData}); break;
            case "select attribute" :
                this.setState({selectAttribute : formData}); break;
            default:
                console.error("Form type not found: " + type);
        }
    };

    render() {
        return (
            <div className={"form-outer"}>
                <form className={"form"}>
                    <InnerForm callBack={this.callbackFormData} text={"name"} />
                    <InnerForm callBack={this.callbackFormData} text={"path"} />
                    <InnerForm callBack={this.callbackFormData} text={"select attribute"} />
                    <InnerForm callBack={this.callbackFormData} text={"attribute name"} />
                    <InnerForm callBack={this.callbackFormData} text={"attribute value"} />
                    <button className={"submit"} type={"submit"} onClick={this.handleSubmit}>submit</button>
                </form>
                <pre>
                    {this.state.result}
                </pre>
            </div>
        );
    }
}
export default Select;

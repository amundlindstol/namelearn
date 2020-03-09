import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";
import Api from "../domain/Api";

class ConnectToSite extends Component {
    constructor(props) {
        super(props);
        this.api = new Api();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            url: '',
            jSessionId: '',
            isConnected: false
        };
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.api.connectToSite(this.state.url, this.state.jSessionId).then(res => {
            console.log(res);
            this.setState({result: res});
            this.props.callBack(this.state);
            return res;
        }).catch(err => this.setState({result: err}));
    };

    callbackFormData = (formData, type) => {
        switch (type) {
            case "url" :
                this.setState({url : formData}); break;
            case "j session id" :
                this.setState({jSessionId: formData}); break;
            default:
                console.error("Form type not found: " + type);
        }
    };

    render() {
        return (
            <div className={"form-outer"}>
                <form className={"form"}>
                    <InnerForm callBack={this.callbackFormData} text={"url"} />
                    <InnerForm callBack={this.callbackFormData} text={"j session id"} />
                    <button className={"submit"} type={"submit"} onClick={this.handleSubmit}>connect</button>
                </form>
                <pre>
                    {this.state.isConnected}
                </pre>
            </div>
        );
    }
}
export default ConnectToSite;

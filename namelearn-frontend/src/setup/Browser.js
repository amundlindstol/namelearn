import React, {Component} from 'react';
import '../css/Setup.css';
import InnerForm from "./InnerForm";
import Api from "../domain/Api";

class Browser extends Component {
    state = {
        path: '//div',
        result: 'nothing'
    };
    constructor(props) {
        super(props);
        this.api = new Api();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.api.browse(this.state.path).then(res => this.setState({result: res})).catch(err => this.setState({result: err}));
    };

    callBack = (formData) => {
        this.setState({path: formData});
    };

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
export default Browser;

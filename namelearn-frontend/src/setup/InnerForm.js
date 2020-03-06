import React, {Component} from 'react';
import '../css/Setup.css';

class InnerForm extends Component {

    handleChange(event) {
        this.props.callBack(event.target.value);
        console.log("change");
        this.setState({path: event.target.value});
    }

    render() {
        return (
            <form className="form-inner">
                <label>
                    {this.props.text}
                    <br/>
                    <input type="text" value={this.props.path} onChange={this.handleChange} />
                </label>
            </form>
        );
    }
}
export default InnerForm;

import React, {Component} from 'react';
import '../css/Setup.css';

class InnerForm extends Component {

    handleChange = (event) => {
        this.setState({value: event.target.value});
        this.props.callBack(event.target.value, this.props.text);
    };

    render() {
        return (
            <div className="form-inner">
                <label>
                    {this.props.text}
                    <br/>
                    <input type="text" onChange={this.handleChange} />
                </label>
            </div>
        );
    }
}
export default InnerForm;

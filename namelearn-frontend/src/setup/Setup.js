import React, {Component} from 'react';
import '../css/App.css';
import '../css/Setup.css';
import Browser from "./Browser";
import Root from "./Root";
import Select from "./Select";
import Api from "../domain/Api";
import ConnectToSite from "./ConnectToSite";

class Setup extends Component {
    constructor(props) {
        super(props);
        this.api = new Api();
        this.state = {
            selector_components: [],
            people : [],
            src_prefix: 'https://wiki.intra.buypass.no',
            j_session_id: '8C3CF812FAC5030A49F1881014A6593C',
            instruction_list: [{
                    name: 'name',
                    path: '/div',
                    attributeName: 'class',
                    attributeValue: 'profile-username',
                    selectAttribute: 'title'
                },
                {
                    name: 'image',
                    path: '/img',
                    attributeName: 'class',
                    attributeValue: 'userLogo logo',
                    selectAttribute: 'src'
                }
            ]
        };
        this.state.selector_components.push(<Select isDefault={true}/>);
    }

    //todo add select on btn click
    render() {
        const { selector_components } = this.state;
        return (
            <div className="App">
                <div className="Setup">
                    <ConnectToSite/>
                    <Root/>
                    <Browser/>
                    {selector_components.length !== 0 && selector_components.map((s, i) => <Select key={i} callBack={this.callbackInstructions}/>)}
                    <button className={"submit"} type="submit" value="+" onClick={this.addSelect}>+</button>
                    <button className={"submit"} type="submit" value="Bake" onClick={this.buildPeople} >Bake</button>
                    <button className={"submit"} onClick={ () => this.props.startGame(this.state.people)}>Play</button>
                    {this.state.people.map((person, i) => {
                        return <img key={i} src={"data:image/png;base64,"+person.picture}/>
                    })
                    }

                    {this.state.people.map((person, i) => {
                        return <pre key={i}>{JSON.stringify(person, null, "\t")}</pre>
                    })}
                </div>
            </div>
        );
    }

    callbackInstructions = (instructions) => {
        const newInstruction = [...this.state.instruction_list, instructions];
        this.setState({instruction_list : newInstruction});
    };

    addSelect = () => {
        const newComponents = [...this.state.selector_components, Select];
        this.setState({selector_components : newComponents});
    };

    buildPeople = () => {
        this.api.build(this.state).then(res => {
                this.setState({people : res});
                console.log(res);
                return res;
        });
    };
}

export default Setup;

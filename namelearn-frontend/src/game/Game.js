import React, {Component} from 'react';
import '../css/App.css';
import DisplayPerson from "./DisplayPerson";

class Game extends Component {
  constructor(props) {
    super(props);
    this.state = {
        hidden: true,
        alternatives: [],
        users: props.people,
        user: {}
    };
  }
  componentDidMount() {
    this.newGame();
  }

  randomPerson() {
    return this.state.users[Math.floor(Math.random() * this.state.users.length)];
  }

  newGame() {
    const newPerson = this.randomPerson();
    let alternatives = [];
    alternatives.push(newPerson);
    alternatives.push(this.randomPerson());
    alternatives.push(this.randomPerson());
    alternatives.push(this.randomPerson());
    alternatives = shuffleList(alternatives);
    this.setState({
      hidden: true,
      alternatives: alternatives,
      user: newPerson
    });
  }

  render() {

    let hidden = this.state.hidden;
    let a = <div>loading...</div>;
    if (this.state.alternatives.length > 2) {
      a = this.displayButtons();
    }

    return (
          <div className="App">
            <div className="App-header">
              <DisplayPerson person={this.state.user} hidden={hidden} />
              {a}
            </div>
          </div>
    );
  }

  displayButtons() {
    const alt = (this.state.alternatives);
    return (<div><button onClick={() => this.click(0)}>{alt[0].attributes.name}</button>
            <button onClick={() => this.click(1)}>{alt[1].attributes.name}</button>
            <button onClick={() => this.click(2)}>{alt[2].attributes.name}</button>
            <button onClick={() => this.click(3)}>{alt[3].attributes.name}</button></div>);
  }

  click(index) {
    console.log(this.state.alternatives[index].attributes.name + " - " + this.state.user.attributes.name);
    if (this.state.alternatives[index].attributes.name === this.state.user.attributes.name) {
      console.log("correct");
      this.setState({hidden: false});
    } else {
      console.log("wrong");
    }
  }
}

function shuffleList(a) {
  for (let i = a.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [a[i], a[j]] = [a[j], a[i]];
  }
  return a;
}

export default Game;

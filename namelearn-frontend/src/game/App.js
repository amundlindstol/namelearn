import React, {Component} from 'react';
import '../css/App.css';
import Api from "../domain/Api";
import DisplayPerson from "./DisplayPerson";

class App extends Component {
  // hidden = true;
  peopleFactory = new Api();
  state = {
    hidden: true,
    alternatives: [],
    users: [], //correct?
    user: {}
  };

  //pass to this component
  componentDidMount() {
    this.peopleFactory.browse()
        .then(people => this.setState({
          users: people.map((person) => person)
        }))
        .then(() => {
          if (this.state.users[0] === undefined) {
            throw Error("Failed to load users");
          }
        })
        .then(() => this.newGame());
    // new GetPictures().downloadAllImages();
  }

  randomPerson() {
    // @ts-ignore
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
    return (<div><button onClick={() => this.click(0)}>{alt[0].fullname}</button>
            <button onClick={() => this.click(1)}>{alt[1].fullname}</button>
            <button onClick={() => this.click(2)}>{alt[2].fullname}</button>
            <button onClick={() => this.click(3)}>{alt[3].fullname}</button></div>);
  }

  click(index) {
    console.log(this.state.alternatives[index].fullname + " - " + this.state.user.fullname);
    if (this.state.alternatives[index].fullname === this.state.user.fullname) {
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

export default App;

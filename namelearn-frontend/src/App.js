import React from 'react';
import Setup from "./setup/Setup";
import Game from "./game/Game";

class App extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
        window: 'SETUP',
        users: []
    };
  }

  render() {
    let  window  = this.state.window === undefined ? 'SETUP' : this.state.window;
    console.log(window);
    console.log(window === 'SETUP');
    return (
        <div>
          {window === 'SETUP' ? (
              <Setup startGame={this.startGame}/>
          ) : window === 'GAME' ? (
              <Game people={this.state.users}/>
          ) : null}
        </div>
    );
  }
  startGame = (people) => {
    console.log("Starting game with:\n" + people);
    this.setState({users: people});
    this.setState({window: 'GAME'});
  };
}

export default App;

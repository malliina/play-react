import React from "react";
import ReactDOM from "react-dom";

class Clock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {date: new Date()};
        this.prefix = props.prefix ? props.prefix : "It is";
    }

    componentDidMount() {
        this.timerID = setInterval(() => this.tick(), 1000);
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    tick() {
        this.setState({date: new Date()});
    }

    render() {
        return (
            <p>{this.prefix} {this.state.date.toLocaleTimeString()}.</p>
        );
    }
}

class Button extends React.Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.state = {isOn: true};
    }

    handleClick() {
        this.setState(prev => ({
            isOn: !prev.isOn
        }));
    }

    render() {
        return (
            <button onClick={this.handleClick}>
                {this.state.isOn ? "ON" : "OFF"}
            </button>
        );
    }
}

class App extends React.Component {
    render() {
        return (
            <div>
                <Clock/>
                <Button/>
            </div>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('app')
);

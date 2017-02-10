import {h, render, Component} from "preact";

/** @jsx h */

class Clock extends Component {
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

    render(props, state) {
        return (
            <p>{this.prefix} {this.state.date.toLocaleTimeString()}.</p>
        );
    }
}

class Button extends Component {
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

    render(props, state) {
        return (
            <button onClick={this.handleClick}>
                {this.state.isOn ? "ON" : "OFF"}
            </button>
        );
    }
}

class App extends Component {
    render(props, state) {
        return (
            <div>
                <Clock prefix={props.clockPrefix}/>
                <Button/>
            </div>
        )
    }
}

render(<App clockPrefix="The time"/>, document.body);

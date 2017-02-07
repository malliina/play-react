import React from 'react';
import ReactDOM from 'react-dom';

class MyCompES6 extends React.Component {
    render() {
        return (<div><span>aaa!!!!!!!!!</span></div>)
    }
}

function component () {
  let element = document.createElement('div');

  element.innerHTML = "raw";

  return element;
}

document.body.appendChild(component());

ReactDOM.render(
    React.createElement(MyCompES6),
    document.getElementById('app')
);

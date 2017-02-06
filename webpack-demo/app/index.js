import React from 'react';
import ReactDOM from 'react-dom';
/*import _ from 'lodash'; */

let MyComp = React.createClass({
    render: function () {
        return (<div><span>he he</span></div>)
    }
});

class MyCompES6 extends React.Component {
    render() {
        return (<div><span>he he!</span></div>)
    }
}

function component () {
  var element = document.createElement('div');

  /* lodash is required for the next line to work */
  element.innerHTML = "raw"; /*_.join(['Hello','webpack'], ' '); */

  return element;
}

document.body.appendChild(component());

ReactDOM.render(
    React.createElement(MyCompES6),
    document.getElementById('app')
);

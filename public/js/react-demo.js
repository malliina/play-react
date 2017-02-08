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
ReactDOM.render(
    React.createElement(MyCompES6),
    document.getElementById('app')
);

// ReactDOM.render(
//     <h1 id="my-heading">
//         <span><em>Hell</em>o</span> world!
//     </h1>,
//     document.getElementById('app')
// );

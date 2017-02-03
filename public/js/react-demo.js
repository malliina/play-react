export const MyComp = React.createClass({
    render: function () {
        return (<div><span>he he</span></div>)
    }
});

ReactDOM.render(
    React.createElement(MyComp),
    document.getElementById('app')
);

// ReactDOM.render(
//     <h1 id="my-heading">
//         <span><em>Hell</em>o</span> world!
//     </h1>,
//     document.getElementById('app')
// );

import * as React from "react";
import * as ReactDOM from "react-dom";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Home } from "./components/home/Home";

ReactDOM.render(
	<Router>
        <Route path="/" component={Home} />
    </Router>,
    document.getElementById("root")
);

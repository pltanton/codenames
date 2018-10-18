import * as React from "react";
import * as ReactDOM from "react-dom";
import { HashRouter as Router, Route, Switch } from "react-router-dom";
import { Home } from "./components/home/Home";
import { Game } from "./components/game/Game";

ReactDOM.render(
	<Router>
        <Switch>
            <Route path="/" exact component={Home} />
            <Route path="/game/:id" component={Game} />
        </Switch>
    </Router>,
    document.getElementById("root")
);
